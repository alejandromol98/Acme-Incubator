
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurInvestmentRoundListMineService implements AbstractListService<Entrepreneur, InvestmentRound> {

	// Internal State ---------------------------------------
	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;

		Collection<InvestmentRound> result;
		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findManyAll(principal.getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kind", "title", "isFinalMode");
	}

}
