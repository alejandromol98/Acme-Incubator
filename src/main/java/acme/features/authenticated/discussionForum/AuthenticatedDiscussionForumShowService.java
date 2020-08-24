
package acme.features.authenticated.discussionForum;

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

		boolean result;
		int forumId;
		DiscussionForum forum;
		String usernames;
		Principal principal;

		forumId = request.getModel().getInteger("id");
		forum = this.repository.findOneById(forumId);
		usernames = forum.getUsers();
		principal = request.getPrincipal();
		result = usernames.contains(principal.getUsername());

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

		String investmentRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("invRoundTicker", investmentRoundTicker);

		request.unbind(entity, model, "title", "moment", "users");
	}

}
