
package acme.features.authenticated.overture;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOvertureShowService implements AbstractShowService<Authenticated, Overture> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedOvertureRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		boolean result;
		int overtureId;
		Overture overture;

		Date date = Calendar.getInstance().getTime();
		overtureId = request.getModel().getInteger("id");
		overture = this.repository.findOneById(overtureId);

		result = this.repository.findManyAll(date).contains(overture);

		return result;
	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "min", "max", "email");
	}

}
