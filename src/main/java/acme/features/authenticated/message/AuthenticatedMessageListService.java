
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.features.authenticated.discussionForum.AuthenticatedDiscussionForumRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	// Internal state -------------------------------------------

	@Autowired
	AuthenticatedMessageRepository			repository;

	@Autowired
	AuthenticatedDiscussionForumRepository	discForumRepository;


	//AbstractLiveService interface ----------------------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		int forumId;
		DiscussionForum forum;
		Principal principal;

		forumId = request.getModel().getInteger("id");
		forum = this.discForumRepository.findOneById(forumId);
		String users = forum.getUsers().replaceAll("\\s", "");
		List<String> usernames = Arrays.asList(users.split(","));
		principal = request.getPrincipal();
		String author = forum.getAuthor().getUserAccount().getUsername();
		for (String u : usernames) {
			if (u.equals(principal.getUsername()) || author.equals(principal.getUsername())) {
				result = true;
			}
		}

		return result;
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
