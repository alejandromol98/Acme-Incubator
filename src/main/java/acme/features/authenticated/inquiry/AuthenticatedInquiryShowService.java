
package acme.features.authenticated.inquiry;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInquiryShowService implements AbstractShowService<Authenticated, Inquiry> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedInquiryRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		boolean result;
		int inquiryId;
		Inquiry inquiry;

		Date date = Calendar.getInstance().getTime();
		inquiryId = request.getModel().getInteger("id");
		inquiry = this.repository.findOneById(inquiryId);

		result = this.repository.findManyAll(date).contains(inquiry);

		return result;
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
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "min", "max", "email");
	}

}
