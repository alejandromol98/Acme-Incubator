
package acme.features.authenticated.accountingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.accountingRecords.Status;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAccountingRecordListService implements AbstractListService<Authenticated, AccountingRecord> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedAccountingRecordRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<AccountingRecord> findMany(final Request<AccountingRecord> request) {
		assert request != null;

		Collection<AccountingRecord> result;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");

		result = this.repository.findManyAll(investmentRoundId, Status.PUBLISHED);
		return result;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "moment");
	}

}
