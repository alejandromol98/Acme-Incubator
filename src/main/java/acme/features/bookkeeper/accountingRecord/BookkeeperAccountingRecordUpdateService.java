
package acme.features.bookkeeper.accountingRecord;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.accountingRecords.Status;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Bookkeeper;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordUpdateService implements AbstractUpdateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {

		boolean result;
		boolean resultPrincipal;
		boolean resultStatus;

		int accountingRecordId;
		AccountingRecord accountingRecord;
		Bookkeeper bookkeeper;
		Principal principal;

		accountingRecordId = request.getModel().getInteger("id");
		accountingRecord = this.repository.findOneById(accountingRecordId);

		bookkeeper = accountingRecord.getBookkeeper();
		principal = request.getPrincipal();
		resultPrincipal = bookkeeper.getUserAccount().getId() == principal.getAccountId();

		resultStatus = accountingRecord.getStatus().equals(Status.DRAFT);

		result = resultPrincipal && resultStatus;

		return result;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "investmentRound", "bookkeeper");

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("id", request.getModel().getInteger("id"));

		request.unbind(entity, model, "title", "status", "body");

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
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
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

			errors.state(request, n <= threshold, "title", "bookkeeper.accountingRecord.form.valid.title");
		}

		//Validate Body Spam
		if (!errors.hasErrors("body")) {
			String body = entity.getBody();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(body.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (body.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "body", "bookkeeper.accountingRecord.form.valid.body");
		}

	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
