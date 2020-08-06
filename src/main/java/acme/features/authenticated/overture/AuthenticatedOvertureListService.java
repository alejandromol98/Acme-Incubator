
package acme.features.authenticated.overture;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedOvertureListService implements AbstractListService<Authenticated, Overture> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedOvertureRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Overture> findMany(final Request<Overture> request) {
		assert request != null;
		Collection<Overture> result;
		Date date = Calendar.getInstance().getTime();

		result = this.repository.findManyAll(date);
		return result;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
	}

}
