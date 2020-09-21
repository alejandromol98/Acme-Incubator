
package acme.features.entrepreneur.monema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.monemas.Monema;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurMonemaUpdateService implements AbstractUpdateService<Entrepreneur, Monema> {

	// Internal State ---------------------------------------------------------

	@Autowired
	EntrepreneurMonemaRepository			repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	// AbstractUpdateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<Monema> request) {
		assert request != null;

		boolean result;
		int monemaId;
		Monema monema;
		Entrepreneur entrepreneur;
		Principal principal;

		monemaId = request.getModel().getInteger("id");
		monema = this.repository.findOneById(monemaId);
		entrepreneur = monema.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

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

		request.unbind(entity, model, "text");

	}

	@Override
	public Monema findOne(final Request<Monema> request) {
		assert request != null;

		Monema result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

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

		//Validate Text Spam
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
	public void update(final Request<Monema> request, final Monema entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
