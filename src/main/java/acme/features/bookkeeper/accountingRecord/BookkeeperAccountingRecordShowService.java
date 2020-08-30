
package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperAccountingRecordShowService implements AbstractShowService<Bookkeeper, AccountingRecord> {

	// Internal State ---------------------------------------
	@Autowired
	BookkeeperAccountingRecordRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		//		boolean result;
		//		int invRoundId;
		//		InvestmentRound invRound;
		//		Bookkeeper bookkeeper;
		//		Principal principal;
		//
		//		invRoundId = request.getModel().getInteger("id");
		//		invRound = this.repository.findOneById(invRoundId);
		//		entrepreneur = invRound.getEntrepreneur();
		//		principal = request.getPrincipal();
		//		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return true;
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

		request.unbind(entity, model, "title", "status", "body", "moment");
	}

}
