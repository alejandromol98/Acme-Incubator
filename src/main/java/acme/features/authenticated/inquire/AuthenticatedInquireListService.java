
package acme.features.authenticated.inquire;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInquireListService implements AbstractListService<Authenticated, Inquire> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedInquireRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Inquire> findMany(final Request<Inquire> request) {
		assert request != null;
		Collection<Inquire> result;
		Date date = Calendar.getInstance().getTime();

		result = this.repository.findManyAll(date);
		return result;
	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
	}

}
