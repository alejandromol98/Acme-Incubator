
package acme.features.bookkeeper.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperInvestmentRoundShowService implements AbstractShowService<Bookkeeper, InvestmentRound> {

	// Internal State ---------------------------------------
	@Autowired
	BookkeeperInvestmentRoundRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		//		boolean result;
		//		int invRoundId;
		//		InvestmentRound invRound;
		//		Bookkeeper bookkeeper;
		//		Principal principal;
		//
		//		invRoundId = request.getModel().getInteger("id");
		//		invRound = this.repository.findOneById(invRoundId);
		//		entrepreneur = invRound.getEntrepreneur();
		//		principal = request.getPrincipal();
		//		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return true;
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

		request.unbind(entity, model, "ticker", "title", "moment", "kind", "description", "amount", "moreInfo", "isFinalMode");
	}

}
