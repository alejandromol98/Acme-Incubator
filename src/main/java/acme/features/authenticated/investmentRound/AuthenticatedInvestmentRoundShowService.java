
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentRoundShowService implements AbstractShowService<Authenticated, InvestmentRound> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedInvestmentRoundRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		Boolean result;
		InvestmentRound invRound;
		Collection<InvestmentRound> validInvRounds;
		int id;

		id = request.getModel().getInteger("id");
		invRound = this.repository.findOneById(id);

		validInvRounds = this.repository.findManyAll();
		validInvRounds.removeAll(this.repository.findInactiveInvestmentRounds());

		result = validInvRounds.contains(invRound);

		return result;
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
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String entrepreneur = entity.getEntrepreneur().getUserAccount().getUsername();
		model.setAttribute("entrepreneur", entrepreneur);

		request.unbind(entity, model, "ticker", "title", "moment", "kind", "description", "amount", "moreInfo");
	}

}
