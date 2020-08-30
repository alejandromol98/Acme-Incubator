
package acme.features.authenticated.inquire;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInquireShowService implements AbstractShowService<Authenticated, Inquire> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedInquireRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;

		boolean result;
		int inquireId;
		Inquire inquire;

		Date date = Calendar.getInstance().getTime();
		inquireId = request.getModel().getInteger("id");
		inquire = this.repository.findOneById(inquireId);

		result = this.repository.findManyAll(date).contains(inquire);

		return result;
	}

	@Override
	public Inquire findOne(final Request<Inquire> request) {
		assert request != null;

		Inquire result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "min", "max", "email");
	}

}
