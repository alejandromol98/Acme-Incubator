
package acme.features.investor.monema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.monemas.Monema;
import acme.entities.roles.Investor;
import acme.features.entrepreneur.monema.EntrepreneurMonemaRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorMonemaShowService implements AbstractShowService<Investor, Monema> {

	// Internal State ---------------------------------------
	@Autowired
	EntrepreneurMonemaRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Monema> request) {
		assert request != null;

		boolean result;
		int monemaId;
		Monema monema;
		InvestmentRound investmentRound;

		monemaId = request.getModel().getInteger("id");
		monema = this.repository.findOneById(monemaId);
		investmentRound = monema.getInvestmentRound();
		result = investmentRound.getIsFinalMode();

		return result;
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
	public void unbind(final Request<Monema> request, final Monema entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String invRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("investmentRound", invRoundTicker);

		request.unbind(entity, model, "text");
	}

}
