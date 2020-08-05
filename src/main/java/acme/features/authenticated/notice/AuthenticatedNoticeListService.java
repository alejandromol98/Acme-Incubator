
package acme.features.authenticated.notice;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedNoticeListService implements AbstractListService<Authenticated, Notice> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedNoticeRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Notice> findMany(final Request<Notice> request) {
		assert request != null;
		Collection<Notice> result;
		Date date = Calendar.getInstance().getTime();

		result = this.repository.findManyAll(date);
		return result;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
	}

}
