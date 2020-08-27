
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.authenticated.accountingRecord.AuthenticatedAccountingRecordRepository;
import acme.features.authenticated.discussionForum.AuthenticatedDiscussionForumRepository;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.features.entrepreneur.workProgramme.EntrepreneurWorkProgrammeRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	// Internal State ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurWorkProgrammeRepository		workProgrammeRepository;

	@Autowired
	AuthenticatedAccountingRecordRepository	accountingRecordRepository;

	@Autowired
	AuthenticatedDiscussionForumRepository	forumRepository;

	@Autowired
	AuthenticatedMessageRepository			messageRepository;


	// AbstractDeleteService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		int invRoundId;
		InvestmentRound invRound;
		Entrepreneur entrepreneur;
		Principal principal;

		invRoundId = request.getModel().getInteger("id");
		invRound = this.repository.findOneById(invRoundId);
		entrepreneur = invRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "isFinalMode", "entrepreneur");
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kind", "title", "description", "amount", "moreInfo");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean result;
		int id;

		id = entity.getId();
		result = this.repository.getCountAppsByInvRound(id) <= 0;
		errors.state(request, result, "ticker", "entrepreneur.investmentRound.form.valid.invalidDelete");

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		int invRoundId = entity.getId();

		Collection<WorkProgramme> workProgrammes = this.repository.getWorkProgrammesByInvRound(invRoundId);
		if (!workProgrammes.isEmpty()) {
			this.workProgrammeRepository.deleteAll(workProgrammes);
		}

		Collection<AccountingRecord> accountingRecords = this.repository.getAccountingRecordsByInvRound(invRoundId);
		if (!accountingRecords.isEmpty()) {
			this.accountingRecordRepository.deleteAll(accountingRecords);
		}

		Collection<Message> messages = this.repository.getMessagesByInvRound(invRoundId);
		if (!messages.isEmpty()) {
			this.messageRepository.deleteAll(messages);
		}

		Collection<DiscussionForum> discussionForums = this.repository.getDiscussionForumsByInvRound(invRoundId);
		if (!discussionForums.isEmpty()) {
			this.forumRepository.deleteAll(discussionForums);
		}

		this.repository.delete(entity);
	}

}
