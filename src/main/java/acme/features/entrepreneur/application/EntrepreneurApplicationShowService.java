
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurApplicationShowService implements AbstractShowService<Entrepreneur, Application> {

	// Internal State ---------------------------------------
	@Autowired
	EntrepreneurApplicationRepository repository;


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
		String applicationInvestor = entity.getInvestor().getUserAccount().getUsername();
		model.setAttribute("applicationInvestor", applicationInvestor);

		request.unbind(entity, model, "ticker", "moment", "statement", "offer", "status", "justification");
	}

}
