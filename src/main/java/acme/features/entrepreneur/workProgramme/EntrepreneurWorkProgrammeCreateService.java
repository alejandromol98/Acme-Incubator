
package acme.features.entrepreneur.workProgramme;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurWorkProgrammeCreateService implements AbstractCreateService<Entrepreneur, WorkProgramme> {

	// Internal State ---------------------------------------------------------

	@Autowired
	EntrepreneurWorkProgrammeRepository		repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	// AbstractCreateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		boolean result;
		boolean resultPrincipal;
		boolean resultBudget = false;

		InvestmentRound investmentRound;
		int invId;
		Entrepreneur entrepreneur;
		int workProgrammesCount;

		invId = request.getModel().getInteger("invId");

		investmentRound = this.investmentRoundRepository.findOneById(invId);
		entrepreneur = investmentRound.getEntrepreneur();

		resultPrincipal = request.getPrincipal().getActiveRoleId() == entrepreneur.getId();

		workProgrammesCount = this.investmentRoundRepository.getCountWorkProgrammesByInvRound(invId);
		if (workProgrammesCount > 0) {
			resultBudget = this.investmentRoundRepository.getSumWorkProgrammesByInvRound(invId) < investmentRound.getAmount().getAmount();
		}
		if (workProgrammesCount == 0) {
			resultBudget = true;
		}

		result = resultPrincipal && resultBudget;

		return result;
	}

	@Override
	public void bind(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "investmentRound");
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("invId", entity.getInvestmentRound().getId());

		request.unbind(entity, model, "title", "deadline", "budget");

	}

	@Override
	public WorkProgramme instantiate(final Request<WorkProgramme> request) {
		WorkProgramme result;

		result = new WorkProgramme();

		Date moment;
		InvestmentRound investmentRound;
		int invRoundId;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		invRoundId = request.getModel().getInteger("invId");
		investmentRound = this.investmentRoundRepository.findOneById(invRoundId);
		result.setInvestmentRound(investmentRound);

		return result;
	}

	@Override
	public void validate(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<Customisation> customisations = (List<Customisation>) this.customisationRepository.findManyAll();
		Customisation customisation = customisations.get(0);
		String[] spam = customisation.getSpamwords().split(",");

		// Validate Budget Euros
		if (!errors.hasErrors("budget")) {
			Boolean result;
			result = entity.getBudget().getCurrency().equals("EUR") || entity.getBudget().getCurrency().equals("€");
			errors.state(request, result, "budget", "entrepreneur.investmentRound.form.valid.amount");
		}

		//Validate budget is less than investment round amount left
		if (!errors.hasErrors("budget")) {
			Boolean result;
			Double finalSum;
			int invId = request.getModel().getInteger("invId");

			InvestmentRound invRound = this.investmentRoundRepository.findOneById(invId);
			Double invRoundAmount = invRound.getAmount().getAmount();

			Double workProgrammesSum = this.investmentRoundRepository.getSumWorkProgrammesByInvRound(invId);
			if (workProgrammesSum != null) {
				finalSum = workProgrammesSum + entity.getBudget().getAmount();
			} else {
				finalSum = entity.getBudget().getAmount();
			}

			result = finalSum <= invRoundAmount;
			errors.state(request, result, "budget", "entrepreneur.workProgramme.form.valid.budgetSum");
		}

		//Validate Title Spam
		if (!errors.hasErrors("title")) {
			String title = entity.getTitle();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(title.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (title.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "title", "entrepreneur.investmentRound.form.valid.title");
		}

		//Validate deadline
		if (!errors.hasErrors("deadline")) {
			Date deadline = entity.getDeadline();
			Date moment = new Date(System.currentTimeMillis());
			boolean result;
			result = deadline.after(moment);
			errors.state(request, result, "deadline", "entrepreneur.workProgramme.form.valid.deadline");
		}

	}

	@Override
	public void create(final Request<WorkProgramme> request, final WorkProgramme entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
