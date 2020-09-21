
package acme.features.investor.monema;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.monemas.Monema;
import acme.entities.roles.Investor;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorMonemaListService implements AbstractListService<Investor, Monema> {

	// Internal State ---------------------------------------
	@Autowired
	InvestorMonemaRepository				repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Monema> request) {
		assert request != null;

		boolean result;
		int invRoundId;
		InvestmentRound invRound;

		invRoundId = request.getModel().getInteger("id");
		invRound = this.investmentRoundRepository.findOneById(invRoundId);
		result = invRound.getIsFinalMode();

		return result;
	}

	@Override
	public Collection<Monema> findMany(final Request<Monema> request) {
		assert request != null;

		Collection<Monema> result;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");

		result = this.repository.findManyAll(investmentRoundId);
		return result;
	}

	@Override
	public void unbind(final Request<Monema> request, final Monema entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text");
	}

}
