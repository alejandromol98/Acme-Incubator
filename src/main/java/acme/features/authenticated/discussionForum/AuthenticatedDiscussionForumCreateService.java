
package acme.features.authenticated.discussionForum;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedDiscussionForumCreateService implements AbstractCreateService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		//Solo se puede crear un discussion forum de los investmentRounds activos
		boolean result;
		int invRoundId;
		InvestmentRound invRound;
		Collection<InvestmentRound> investmentRoundsTotal;
		Collection<InvestmentRound> investmentRoundsInactive;

		invRoundId = request.getModel().getInteger("invId");
		invRound = this.investmentRoundRepository.findOneById(invRoundId);

		investmentRoundsTotal = this.investmentRoundRepository.findManyAll();
		investmentRoundsInactive = this.investmentRoundRepository.findInactiveInvestmentRounds();
		investmentRoundsTotal.removeAll(investmentRoundsInactive);

		result = investmentRoundsTotal.contains(invRound);

		return result;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "investmentRound", "author");

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("invId", entity.getInvestmentRound().getId());

		request.unbind(entity, model, "title", "users");

	}

	@Override
	public DiscussionForum instantiate(final Request<DiscussionForum> request) {
		DiscussionForum result;

		result = new DiscussionForum();

		Date moment;
		InvestmentRound investmentRound;
		int invRoundId;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		invRoundId = request.getModel().getInteger("invId");
		investmentRound = this.investmentRoundRepository.findOneById(invRoundId);
		result.setInvestmentRound(investmentRound);

		Integer authorId = request.getPrincipal().getAccountId();
		Authenticated author = this.repository.findAuthenticatedByUserAccountId(authorId);
		result.setAuthor(author);

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

		//Validate Users Spam
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
	public void create(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		String result = entity.getUsers();
		String users = entity.getUsers().replaceAll("\\s", "");
		List<String> usernames = Arrays.asList(users.split(","));

		String entrepreneur = entity.getInvestmentRound().getEntrepreneur().getUserAccount().getUsername();
		Collection<String> investorsUsernames = this.investmentRoundRepository.findUsernameInvestorsByInvestmentRound(request.getModel().getInteger("invId"));
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
