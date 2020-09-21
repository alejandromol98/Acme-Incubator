
package acme.features.entrepreneur.monema;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.monemas.Monema;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurMonemaCreateService implements AbstractCreateService<Entrepreneur, Monema> {

	// Internal State ---------------------------------------------------------

	@Autowired
	EntrepreneurMonemaRepository			repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	// AbstractCreateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<Monema> request) {
		assert request != null;

		boolean result;
		boolean resultPrincipal;
		boolean resultMonema;

		InvestmentRound investmentRound;
		int invId;
		Entrepreneur entrepreneur;

		invId = request.getModel().getInteger("invId");

		investmentRound = this.investmentRoundRepository.findOneById(invId);
		entrepreneur = investmentRound.getEntrepreneur();

		resultPrincipal = request.getPrincipal().getActiveRoleId() == entrepreneur.getId();

		Collection<Monema> monemas = this.repository.findManyAll(invId);
		resultMonema = monemas.isEmpty();

		result = resultPrincipal && resultMonema;

		return result;
	}

	@Override
	public void bind(final Request<Monema> request, final Monema entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "investmentRound");
	}

	@Override
	public void unbind(final Request<Monema> request, final Monema entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("invId", entity.getInvestmentRound().getId());

		request.unbind(entity, model, "text");

	}

	@Override
	public Monema instantiate(final Request<Monema> request) {
		Monema result;

		result = new Monema();

		InvestmentRound investmentRound;
		int invRoundId;

		invRoundId = request.getModel().getInteger("invId");
		investmentRound = this.investmentRoundRepository.findOneById(invRoundId);
		result.setInvestmentRound(investmentRound);

		return result;
	}

	@Override
	public void validate(final Request<Monema> request, final Monema entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<Customisation> customisations = (List<Customisation>) this.customisationRepository.findManyAll();
		Customisation customisation = customisations.get(0);
		String[] spam = customisation.getSpamwords().split(",");

		//Validate Title Spam
		if (!errors.hasErrors("text")) {
			String text = entity.getText();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(text.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (text.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "text", "entrepreneur.monema.form.valid.text");
		}

	}

	@Override
	public void create(final Request<Monema> request, final Monema entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
