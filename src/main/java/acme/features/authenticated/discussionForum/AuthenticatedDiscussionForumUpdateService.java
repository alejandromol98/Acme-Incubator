
package acme.features.authenticated.discussionForum;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.discussionForums.DiscussionForum;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedDiscussionForumUpdateService implements AbstractUpdateService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {

		boolean result;

		int discForId;
		DiscussionForum discussionForum;
		Authenticated author;
		Principal principal;

		discForId = request.getModel().getInteger("id");
		discussionForum = this.repository.findOneById(discForId);

		author = discussionForum.getAuthor();
		principal = request.getPrincipal();
		result = author == this.repository.findAuthenticatedByUserAccountId(principal.getAccountId());

		return result;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "investmentRound");

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("id", request.getModel().getInteger("id"));
		String authorUsername = entity.getAuthor().getUserAccount().getUsername();
		model.setAttribute("authorUsername", authorUsername);

		request.unbind(entity, model, "title", "users");

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
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

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

			errors.state(request, n <= threshold, "title", "authenticated.discussionForum.form.valid.title");
		}

		//Validate Body Spam
		if (!errors.hasErrors("users")) {
			String users = entity.getUsers();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(users.split(",").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (users.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "users", "authenticated.discussionForum.form.valid.users");
		}

	}

	@Override
	public void update(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		String result = entity.getUsers();
		String users = entity.getUsers().replaceAll("\\s", "");
		List<String> usernames = Arrays.asList(users.split(","));

		String entrepreneur = entity.getInvestmentRound().getEntrepreneur().getUserAccount().getUsername();
		Collection<String> investorsUsernames = this.investmentRoundRepository.findUsernameInvestorsByInvestmentRound(entity.getInvestmentRound().getId());
		if (!usernames.contains(entrepreneur)) {
			result = result + "," + entrepreneur;
		}
		for (String investor : investorsUsernames) {
			if (!usernames.contains(investor)) {
				result = result + "," + investor;
			}
		}

		entity.setUsers(result);

		this.repository.save(entity);

	}

}
