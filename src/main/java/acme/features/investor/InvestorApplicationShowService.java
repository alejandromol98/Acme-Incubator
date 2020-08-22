
package acme.features.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorApplicationShowService implements AbstractShowService<Investor, Application> {

	// Internal State ---------------------------------------
	@Autowired
	InvestorApplicationRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int appId;
		Application application;
		Investor investor;
		Principal principal;

		appId = request.getModel().getInteger("id");
		application = this.repository.findOneById(appId);
		investor = application.getInvestor();
		principal = request.getPrincipal();
		result = investor.getUserAccount().getId() == principal.getAccountId();

		return result;
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
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String investmentRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("investmentRoundTicker", investmentRoundTicker);
		Money investmentRoundAmount = entity.getInvestmentRound().getAmount();
		model.setAttribute("investmentRoundAmount", investmentRoundAmount);

		request.unbind(entity, model, "ticker", "moment", "statement", "offer");
	}

}
