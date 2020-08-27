
package acme.features.entrepreneur.investmentRound;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	// Internal State ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	// AbstractUpdateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		int invRoundId;
		InvestmentRound invRound;
		Entrepreneur entrepreneur;
		Principal principal;

		invRoundId = request.getModel().getInteger("id");
		invRound = this.repository.findOneById(invRoundId);
		entrepreneur = invRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "entrepreneur");
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kind", "title", "description", "amount", "moreInfo", "isFinalMode");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<Customisation> customisations = (List<Customisation>) this.customisationRepository.findManyAll();
		Customisation customisation = customisations.get(0);
		String[] spam = customisation.getSpamwords().split(",");

		// Validate Ticker
		if (!errors.hasErrors("ticker")) {

			Date moment = new Date(System.currentTimeMillis() - 1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(moment);
			Integer year = calendar.get(Calendar.YEAR);
			boolean result = true;

			String activitySector = entity.getEntrepreneur().getActivitySector();
			String tickerActivitySector = entity.getTicker().substring(0, entity.getTicker().indexOf("-"));
			String tickerYear = entity.getTicker().substring(entity.getTicker().indexOf("-") + 1, entity.getTicker().indexOf("-") + 3);
			String tickerNumber = entity.getTicker().substring(entity.getTicker().indexOf("-") + 4, entity.getTicker().length());

			if (activitySector.length() == 1) {
				result = tickerActivitySector.matches("[A-Z]+") && entity.getTicker().startsWith(activitySector.toUpperCase()) && entity.getTicker().substring(1, 3).equals("XX") && tickerYear.equals(year.toString().substring(2))
					&& tickerNumber.matches("^[0-9]{6}$");
			}
			if (activitySector.length() == 2) {
				result = tickerActivitySector.matches("[A-Z]+") && entity.getTicker().substring(0, 2).equals(activitySector.toUpperCase()) && entity.getTicker().substring(2, 3).equals("X") && tickerYear.equals(year.toString().substring(2))
					&& tickerNumber.matches("^[0-9]{6}$");
			}
			if (activitySector.length() > 2) {
				result = tickerActivitySector.matches("[A-Z]+") && tickerActivitySector.equals(activitySector.substring(0, 3).toUpperCase()) && tickerYear.equals(year.toString().substring(2)) && tickerNumber.matches("^[0-9]{6}$");
			}

			errors.state(request, result, "ticker", "entrepreneur.investmentRound.form.valid.ticker");
		}

		// Validate isFinalMode: Solo se podrá poner en final mode si todas las applications suman el "amount" del invRound.
		if (!errors.hasErrors("isFinalMode") && entity.getIsFinalMode() == true) {
			Boolean result;
			Integer numWorkProgrammes = this.repository.getCountWorkProgrammesByInvRound(request.getModel().getInteger("id"));
			if (numWorkProgrammes > 0) {
				Double sumaApplications = this.repository.getSumWorkProgrammesByInvRound(request.getModel().getInteger("id"));
				Double amountInvRound = entity.getAmount().getAmount();

				result = sumaApplications >= amountInvRound;
			} else {
				result = false;
			}

			errors.state(request, result, "isFinalMode", "entrepreneur.investmentRound.form.valid.isFinalMode");
		}

		// Validate amount
		if (!errors.hasErrors("amount")) {
			Boolean result;
			result = entity.getAmount().getCurrency().equals("EUR") || entity.getAmount().getCurrency().equals("€");
			errors.state(request, result, "amount", "entrepreneur.investmentRound.form.valid.amount");
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

		//Validate Description Spam
		if (!errors.hasErrors("title")) {
			String descripcion = entity.getDescription();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(descripcion.split(" ").length) * customisation.getThreshold() / 100;
			spam = customisation.getSpamwords().split(",");

			for (String s : spam) {
				if (descripcion.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "description", "entrepreneur.investmentRound.form.valid.description");
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

}
