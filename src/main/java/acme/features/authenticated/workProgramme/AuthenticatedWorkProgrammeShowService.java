
package acme.features.authenticated.workProgramme;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedWorkProgrammeShowService implements AbstractShowService<Authenticated, WorkProgramme> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedWorkProgrammeRepository	repository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		Boolean result;
		WorkProgramme workProgramme;
		InvestmentRound invRound;
		Collection<InvestmentRound> validInvRounds;
		int id;

		id = request.getModel().getInteger("id");
		workProgramme = this.repository.findOneById(id);
		invRound = workProgramme.getInvestmentRound();

		validInvRounds = this.investmentRoundRepository.findManyAll();
		validInvRounds.removeAll(this.investmentRoundRepository.findInactiveInvestmentRounds());

		result = validInvRounds.contains(invRound);

		return result;
	}

	@Override
	public WorkProgramme findOne(final Request<WorkProgramme> request) {
		assert request != null;

		WorkProgramme result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "moment", "budget");
	}

}
