
package acme.features.anonymous.molinaBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.molinaBulletins.MolinaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousMolinaBulletinCreateService implements AbstractCreateService<Anonymous, MolinaBulletin> {

	// Internal State ---------------------------------------
	@Autowired
	AnonymousMolinaBulletinRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<MolinaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public MolinaBulletin instantiate(final Request<MolinaBulletin> request) {
		assert request != null;

		MolinaBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new MolinaBulletin();
		result.setTitle("Bulletin Title");
		result.setAuthor("John Doe");
		result.setText("Lore ipsum");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<MolinaBulletin> request, final MolinaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "text");
	}

	@Override
	public void bind(final Request<MolinaBulletin> request, final MolinaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<MolinaBulletin> request, final MolinaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MolinaBulletin> request, final MolinaBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
