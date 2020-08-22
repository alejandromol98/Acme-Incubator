
package acme.features.investor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class InvestorApplicationListMineService implements AbstractListService<Investor, Application> {

	// Internal State ---------------------------------------
	@Autowired
	InvestorApplicationRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;

		Collection<Application> result;
		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findManyAll(principal.getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String investmentRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("investmentRoundTicker", investmentRoundTicker);

		request.unbind(entity, model, "ticker", "statement", "offer");
	}

}
