
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	// Internal state -------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


	//AbstractLiveService interface ----------------------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		int discForumId;

		discForumId = request.getModel().getInteger("id");
		result = this.repository.findManyAll(discForumId);

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String author = entity.getAuthor().getUserAccount().getUsername();
		model.setAttribute("author", author);

		request.unbind(entity, model, "title", "moment");
	}

}
