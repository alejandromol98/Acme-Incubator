
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDiscussionForumListMineService implements AbstractListService<Authenticated, DiscussionForum> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<DiscussionForum> findMany(final Request<DiscussionForum> request) {
		assert request != null;

		Collection<DiscussionForum> result;
		Collection<DiscussionForum> forums;
		String principalUsername;

		principalUsername = request.getPrincipal().getUsername();

		forums = this.repository.findManyAll();
		result = new ArrayList<DiscussionForum>();

		for (DiscussionForum forum : forums) {
			String users = forum.getUsers().replaceAll("\\s", "");
			List<String> usernames = Arrays.asList(users.split(","));
			String author = forum.getAuthor().getUserAccount().getUsername();
			for (String u : usernames) {
				if ((u.equals(principalUsername) || author.equals(principalUsername)) && !result.contains(forum)) {
					result.add(forum);
				}
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String investmentRoundTicker = entity.getInvestmentRound().getTicker();
		model.setAttribute("invRoundTicker", investmentRoundTicker);

		request.unbind(entity, model, "title", "moment");
	}

}
