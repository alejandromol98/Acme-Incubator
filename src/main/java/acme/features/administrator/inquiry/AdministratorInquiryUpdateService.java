
package acme.features.administrator.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInquiryUpdateService implements AbstractUpdateService<Administrator, Inquiry> {

	// Internal State ---------------------------------------------------------

	@Autowired
	AdministratorInquiryRepository repository;


	// AbstractUpdateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "min", "max", "email");

	}

	@Override
	public Inquiry findOne(final Request<Inquiry> request) {
		assert request != null;

		Inquiry result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValidmin;
		if (!errors.hasErrors("min")) {
			isValidmin = entity.getMin().getCurrency().equals("EUR") || entity.getMin().getCurrency().equals("€");
			errors.state(request, isValidmin, "min", "administrator.inquiry.form.error.invalidmoney");
		}

		Boolean isValidmax;
		if (!errors.hasErrors("max")) {
			isValidmax = entity.getMax().getCurrency().equals("EUR") || entity.getMax().getCurrency().equals("€");
			errors.state(request, isValidmax, "max", "administrator.inquiry.form.error.invalidmoney");
		}

	}

	@Override
	public void update(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
