
package acme.features.investor.application;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.Status;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.monemas.Monema;
import acme.entities.roles.Investor;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.features.authenticated.investor.AuthenticatedInvestorRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	// Internal State ---------------------------------------------------------

	@Autowired
	InvestorApplicationRepository			repository;

	@Autowired
	AuthenticatedInvestorRepository			investorRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	// AbstractCreateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int invRoundId;
		InvestmentRound invRound;
		Collection<InvestmentRound> investmentRoundsTotal;
		Collection<InvestmentRound> investmentRoundsInactive;

		invRoundId = request.getModel().getInteger("invId");
		invRound = this.investmentRoundRepository.findOneById(invRoundId);

		investmentRoundsTotal = this.investmentRoundRepository.findManyAll();
		investmentRoundsInactive = this.investmentRoundRepository.findInactiveInvestmentRounds();
		investmentRoundsTotal.removeAll(investmentRoundsInactive);

		result = investmentRoundsTotal.contains(invRound);

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "status", "investor", "investmentRound");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		InvestmentRound investmentRound;
		int invRoundId;

		invRoundId = request.getModel().getInteger("invId");
		investmentRound = this.investmentRoundRepository.findOneById(invRoundId);

		model.setAttribute("investmentRoundTicker", investmentRound.getTicker());
		model.setAttribute("investmentRoundAmount", investmentRound.getAmount());
		model.setAttribute("invId", entity.getInvestmentRound().getId());

		List<Monema> monemas = (List<Monema>) this.investmentRoundRepository.findMonemaByInvRound(invRoundId);
		if (!monemas.isEmpty()) {
			Monema problem = monemas.get(0);
			model.setAttribute("monemaId", problem.getId());
		}

		request.unbind(entity, model, "ticker", "statement", "offer", "offerMonema", "link", "password");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();

		Date moment;
		Investor investor;
		InvestmentRound investmentRound;
		int investorId;
		int invRoundId;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		investorId = request.getPrincipal().getAccountId();
		investor = this.investorRepository.findOneInvestorByUserAccountId(investorId);
		result.setInvestor(investor);

		result.setStatus(Status.PENDING);

		invRoundId = request.getModel().getInteger("invId");
		investmentRound = this.investmentRoundRepository.findOneById(invRoundId);
		result.setInvestmentRound(investmentRound);

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

		// Validate Ticker
		if (!errors.hasErrors("ticker")) {

			Date moment = new Date(System.currentTimeMillis() - 1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(moment);
			Integer year = calendar.get(Calendar.YEAR);
			boolean result = true;

			String activitySector = entity.getInvestor().getActivitySector();
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

		// Validate Offer
		if (!errors.hasErrors("offer")) {
			Boolean result;
			result = entity.getOffer().getCurrency().equals("EUR") || entity.getOffer().getCurrency().equals("€");
			errors.state(request, result, "offer", "entrepreneur.investmentRound.form.valid.amount");
		}

		//Validate Statement Spam
		if (!errors.hasErrors("statement")) {
			String statement = entity.getStatement();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(statement.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (statement.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "statement", "entrepreneur.investmentRound.form.valid.title");
		}

		//Control Check validation

		if (entity.getOfferMonema() != null) {

			//Validate Problem Spam
			if (!errors.hasErrors("offerProblem")) {
				String offerProblem = entity.getOfferMonema();
				Integer n = 0;

				Double threshold;

				threshold = Double.valueOf(offerProblem.split(" ").length) * customisation.getThreshold() / 100;

				for (String s : spam) {
					if (offerProblem.toLowerCase().contains(s.toLowerCase())) {
						n++;
					}
				}

				errors.state(request, n <= threshold, "offerMonema", "investor.application.form.valid.offerMonema");
			}
			// Validate if exist an password, it must exist a link
			if (!errors.hasErrors("offerProblem")) {
				boolean result;
				String link = entity.getLink();
				if (!link.isEmpty()) {
					result = !entity.getOfferMonema().isEmpty();
				} else {
					result = true;
				}
				errors.state(request, result, "offerMonema", "investor.application.form.valid.offerMonemaLink");
			}
		}

		if (entity.getLink() != null) {

			// Validate if exist a password, it must exist a link
			if (!errors.hasErrors("link")) {
				boolean result;
				String password = entity.getPassword();
				if (!password.isEmpty()) {
					result = !entity.getLink().isEmpty();
				} else {
					result = true;
				}
				errors.state(request, result, "link", "investor.application.form.valid.linkPassword");
			}
		}

		if (entity.getPassword() != null) {

			// Validate password characters
			if (!errors.hasErrors("password")) {
				boolean result;
				int numLetters = 0;
				int numDigits = 0;
				int numSymbols = 0;
				String password = entity.getPassword();
				if (!password.isEmpty()) {
					for (int i = 0; i < password.length(); i++) {
						Character c = password.charAt(i);
						if (Character.isDigit(c)) {
							numDigits += 1;
						}
						if (Character.isLetter(c)) {
							numLetters += 1;
						}
						if (!Character.isDigit(c) && !Character.isLetter(c) && !c.equals(' ')) {
							numSymbols += 1;
						}
					}

					result = password.length() >= 10 && numDigits >= 1 && numLetters >= 1 && numSymbols >= 1;
				} else {
					result = true;
				}
				errors.state(request, result, "password", "investor.application.form.valid.passwordCharacters");
			}
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
