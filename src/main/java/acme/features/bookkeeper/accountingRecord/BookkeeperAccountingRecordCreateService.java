
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.bookkeeper.AuthenticatedBookkeeperRepository;
import acme.features.bookkeeper.investmentRound.BookkeeperInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	AuthenticatedBookkeeperRepository		bookkeeperRepository;

	@Autowired
	BookkeeperInvestmentRoundRepository		investmentRoundRepository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

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

		model.setAttribute("invId", entity.getInvestmentRound().getId());

		request.unbind(entity, model, "title", "status", "body");

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		AccountingRecord result;

		result = new AccountingRecord();

		Date moment;
		InvestmentRound investmentRound;
		int invRoundId;
		Bookkeeper bookkeeper;
		int bookkeeperId;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		invRoundId = request.getModel().getInteger("invId");
		investmentRound = this.investmentRoundRepository.findOneById(invRoundId);
		result.setInvestmentRound(investmentRound);

		bookkeeperId = request.getPrincipal().getAccountId();
		bookkeeper = this.bookkeeperRepository.findOneBookkeeperByUserAccountId(bookkeeperId);
		result.setBookkeeper(bookkeeper);

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
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
