
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.discussionForum.AuthenticatedDiscussionForumRepository;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository			repository;

	@Autowired
	AuthenticatedDiscussionForumRepository	discForumRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		int forumId;
		DiscussionForum forum;
		Principal principal;

		forumId = request.getModel().getInteger("discForId");
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
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "forum", "author");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("discForId", request.getModel().getInteger("discForId"));

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

		request.unbind(entity, model, "title", "body");

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;

		result = new Message();

		Date moment;
		DiscussionForum discForum;
		int discForumId;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		discForumId = request.getModel().getInteger("discForId");
		discForum = this.discForumRepository.findOneById(discForumId);
		result.setForum(discForum);

		Integer authorId = request.getPrincipal().getAccountId();
		Authenticated author = this.discForumRepository.findAuthenticatedByUserAccountId(authorId);
		result.setAuthor(author);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Validate checkbox
		Boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "anonymous.user-account.error.must-accept");

		List<Customisation> customisations = (List<Customisation>) this.customisationRepository.findManyAll();
		Customisation customisation = customisations.get(0);
		String[] spam = customisation.getSpamwords().split(",");

		//Validate Title Spam
		if (!errors.hasErrors("title")) {
			String title = entity.getTitle();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(title.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (title.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "title", "authenticated.message.form.valid.title");
		}

		//Validate Tags Spam
		if (!errors.hasErrors("tags")) {
			String tags = entity.getTags();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(tags.split(",").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (tags.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "tags", "authenticated.message.form.valid.tags");
		}

		//Validate Body Spam
		if (!errors.hasErrors("body")) {
			String body = entity.getBody();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(body.split(",").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (body.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "body", "authenticated.message.form.valid.body");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
