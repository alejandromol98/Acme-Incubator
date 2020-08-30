
package acme.features.entrepreneur.workProgramme;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurWorkProgrammeListService implements AbstractListService<Entrepreneur, WorkProgramme> {

	// Internal State ---------------------------------------
	@Autowired
	EntrepreneurWorkProgrammeRepository		repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		boolean result;
		int invRoundId;
		InvestmentRound invRound;
		Entrepreneur entrepreneur;
		Principal principal;

		invRoundId = request.getModel().getInteger("id");
		invRound = this.investmentRoundRepository.findOneById(invRoundId);
		entrepreneur = invRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public Collection<WorkProgramme> findMany(final Request<WorkProgramme> request) {
		assert request != null;

		Collection<WorkProgramme> result;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");

		result = this.repository.findManyAll(investmentRoundId);
		return result;
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "budget", "deadline");
	}

}
