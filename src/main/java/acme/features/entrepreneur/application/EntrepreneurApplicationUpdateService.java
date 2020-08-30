
package acme.features.entrepreneur.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.Status;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	// Internal State ---------------------------------------
	@Autowired
	EntrepreneurApplicationRepository		repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int appId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;

		appId = request.getModel().getInteger("id");
		application = this.repository.findOneById(appId);
		entrepreneur = application.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String investmentRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("investmentRoundTicker", investmentRoundTicker);
		Money investmentRoundAmount = entity.getInvestmentRound().getAmount();
		model.setAttribute("investmentRoundAmount", investmentRoundAmount);
		String applicationInvestor = entity.getInvestor().getUserAccount().getUsername();
		model.setAttribute("applicationInvestor", applicationInvestor);

		request.unbind(entity, model, "ticker", "moment", "statement", "offer", "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<Customisation> customisations = (List<Customisation>) this.customisationRepository.findManyAll();
		Customisation customisation = customisations.get(0);
		String[] spam = customisation.getSpamwords().split(",");

		// Validate justification spam
		if (!errors.hasErrors("justification")) {
			String justification = entity.getJustification();
			Integer n = 0;
			Double threshold;

			threshold = Double.valueOf(justification.split(" ").length) * customisation.getThreshold() / 100;
			spam = customisation.getSpamwords().split(",");

			for (String s : spam) {
				if (justification.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}
			errors.state(request, n <= threshold, "justification", "entrepreneur.application.form.valid.justificationSpam");
		}

		// If application is rejected, it must have a justification
		if (!errors.hasErrors("justification") && !errors.hasErrors("status")) {
			boolean result;
			result = entity.getStatus() != Status.REJECTED || entity.getStatus() == Status.REJECTED && !entity.getJustification().isEmpty();
			errors.state(request, result, "justification", "entrepreneur.application.form.valid.justificationStatus");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		result.setStatus(entity.getStatus());
		if (result.getStatus() == Status.REJECTED || result.getStatus() == Status.ACCEPTED) {
			result.setJustification(entity.getJustification());
		}

		this.repository.save(result);
	}
}
