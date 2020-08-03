
package acme.features.anonymous.molinaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.molinaBulletins.MolinaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousMolinaBulletinListService implements AbstractListService<Anonymous, MolinaBulletin> {

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
	public Collection<MolinaBulletin> findMany(final Request<MolinaBulletin> request) {
		assert request != null;

		Collection<MolinaBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<MolinaBulletin> request, final MolinaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "text", "moment");
	}

}
