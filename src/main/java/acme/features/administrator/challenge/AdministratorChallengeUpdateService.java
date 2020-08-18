
package acme.features.administrator.challenge;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	// Internal State ---------------------------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	// AbstractUpdateService<Administrator, Inquire> Interface -----------------

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalRookie", "goalAverage", "goalExpert", "rewardRookie", "rewardAverage", "rewardExpert");

	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValidRookie;
		if (!errors.hasErrors("rewardRookie")) {
			isValidRookie = entity.getRewardRookie().getCurrency().equals("EUR") || entity.getRewardRookie().getCurrency().equals("€");
			errors.state(request, isValidRookie, "rewardRookie", "administrator.challenge.form.error.invalidmoney");
		}

		Boolean isValidAverage;
		if (!errors.hasErrors("rewardAverage")) {
			isValidAverage = entity.getRewardAverage().getCurrency().equals("EUR") || entity.getRewardAverage().getCurrency().equals("€");
			errors.state(request, isValidAverage, "rewardAverage", "administrator.challenge.form.error.invalidmoney");
		}

		Boolean isValidExpert;
		if (!errors.hasErrors("rewardExpert")) {
			isValidExpert = entity.getRewardExpert().getCurrency().equals("EUR") || entity.getRewardExpert().getCurrency().equals("€");
			errors.state(request, isValidExpert, "rewardExpert", "administrator.challenge.form.error.invalidmoney");
		}

		Boolean isValidDeadline;
		if (!errors.hasErrors("deadline")) {
			Date fechaProximoMes = DateUtils.addMonths(new Date(System.currentTimeMillis()), 1);
			isValidDeadline = entity.getDeadline().after(fechaProximoMes);
			errors.state(request, isValidDeadline, "deadline", "administrator.challenge.form.error.invaliddeadline");
		}

	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
