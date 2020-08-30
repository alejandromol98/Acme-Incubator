
package acme.features.authenticated.accountingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAccountingRecordShowService implements AbstractShowService<Authenticated, AccountingRecord> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedAccountingRecordRepository	repository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		Boolean result;
		AccountingRecord accRecord;
		InvestmentRound invRound;
		Collection<InvestmentRound> validInvRounds;
		int id;

		id = request.getModel().getInteger("id");
		accRecord = this.repository.findOneById(id);
		invRound = accRecord.getInvestmentRound();

		validInvRounds = this.investmentRoundRepository.findManyAll();
		validInvRounds.removeAll(this.investmentRoundRepository.findInactiveInvestmentRounds());

		result = validInvRounds.contains(invRound);

		return result;
	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String bookkeeper = entity.getBookkeeper().getUserAccount().getUsername();
		model.setAttribute("bookkeeper", bookkeeper);

		request.unbind(entity, model, "title", "status", "moment", "body");
	}

}
