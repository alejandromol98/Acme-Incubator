
package acme.features.authenticated.workProgramme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedWorkProgrammeShowService implements AbstractShowService<Authenticated, WorkProgramme> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedWorkProgrammeRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		//		boolean result;
		//		int invRoundId;
		//		WorkProgramme invRound;
		//		Entrepreneur entrepreneur;
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
