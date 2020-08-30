
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedDiscussionForumDeleteService implements AbstractDeleteService<Authenticated, DiscussionForum> {

	// Internal State ---------------------------------------------------------

	@Autowired
	AuthenticatedDiscussionForumRepository	repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	invRoundRepository;

	@Autowired
	AuthenticatedMessageRepository			messageRepository;


	// AbstractDeleteService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		boolean result;
		int discForId;
		DiscussionForum discFor;
		UserAccount author;
		Principal principal;

		discForId = request.getModel().getInteger("id");
		discFor = this.repository.findOneById(discForId);
		author = discFor.getAuthor().getUserAccount();
		principal = request.getPrincipal();
		result = author.getId() == principal.getAccountId();

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

	}

	@Override
	public void delete(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		int discForId = entity.getId();

		Collection<Message> messages = this.repository.findMessagesByDiscussionForumId(discForId);
		if (!messages.isEmpty()) {
			this.messageRepository.deleteAll(messages);
		}

		this.repository.delete(entity);
	}

}
