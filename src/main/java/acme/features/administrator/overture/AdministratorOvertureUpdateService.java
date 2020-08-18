
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	// Internal State ---------------------------------------------------------

	@Autowired
	AdministratorOvertureRepository repository;


	// AbstractUpdateService<Administrator, Overture> Interface -----------------

	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "min", "max", "email");

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
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValidmin;
		if (!errors.hasErrors("min")) {
			isValidmin = entity.getMin().getCurrency().equals("EUR") || entity.getMin().getCurrency().equals("€");
			errors.state(request, isValidmin, "min", "administrator.overture.form.error.invalidmoney");
		}

		Boolean isValidmax;
		if (!errors.hasErrors("max")) {
			isValidmax = entity.getMax().getCurrency().equals("EUR") || entity.getMax().getCurrency().equals("€");
			errors.state(request, isValidmax, "max", "administrator.overture.form.error.invalidmoney");
		}

	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
