
package acme.features.authenticated.discussionForum;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDiscussionForumShowService implements AbstractShowService<Authenticated, DiscussionForum> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		boolean result = false;
		int forumId;
		DiscussionForum forum;
		Principal principal;

		forumId = request.getModel().getInteger("id");
		forum = this.repository.findOneById(forumId);
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
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Boolean isAuthor = entity.getAuthor().getUserAccount().getId() == request.getPrincipal().getAccountId();
		model.setAttribute("isAuthor", isAuthor);
		String investmentRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("invRoundTicker", investmentRoundTicker);
		String authorUsername = entity.getAuthor().getUserAccount().getUsername();
		model.setAttribute("authorUsername", authorUsername);

		request.unbind(entity, model, "title", "moment", "users");
	}

}
