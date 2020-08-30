
package acme.features.anonymous.notice;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousNoticeShowService implements AbstractShowService<Anonymous, Notice> {

	// Internal State ---------------------------------------
	@Autowired
	AnonymousNoticeRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		boolean result;
		int noticeId;
		Notice notice;

		Date date = Calendar.getInstance().getTime();
		noticeId = request.getModel().getInteger("id");
		notice = this.repository.findOneById(noticeId);

		result = this.repository.findManyAll(date).contains(notice);

		return result;
	}

	@Override
	public Notice findOne(final Request<Notice> request) {
		assert request != null;

		Notice result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerPicture", "title", "moment", "deadline", "body");
	}

}
