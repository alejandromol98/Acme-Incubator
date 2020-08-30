
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state --------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


	// AbstractShowService interface -----------------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		int messageId;
		Message message;
		Principal principal;

		messageId = request.getModel().getInteger("id");
		message = this.repository.findOneById(messageId);
		String users = message.getForum().getUsers().replaceAll("\\s", "");
		List<String> usernames = Arrays.asList(users.split(","));
		principal = request.getPrincipal();
		String author = message.getForum().getAuthor().getUserAccount().getUsername();
		for (String u : usernames) {
			if (u.equals(principal.getUsername()) || author.equals(principal.getUsername())) {
				result = true;
			}
		}

		return result;

	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String author = entity.getAuthor().getUserAccount().getUsername();
		model.setAttribute("author", author);

		request.unbind(entity, model, "title", "moment", "tags", "body");
	}

}
