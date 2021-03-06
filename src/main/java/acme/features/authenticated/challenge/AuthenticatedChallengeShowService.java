
package acme.features.authenticated.challenge;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChallengeShowService implements AbstractShowService<Authenticated, Challenge> {

	// Internal State ---------------------------------------
	@Autowired
	AuthenticatedChallengeRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		boolean result;
		int challengeId;
		Challenge challenge;

		Date date = Calendar.getInstance().getTime();
		challengeId = request.getModel().getInteger("id");
		challenge = this.repository.findOneById(challengeId);

		result = this.repository.findManyAll(date).contains(challenge);

		return result;
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
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalRookie", "goalAverage", "goalExpert", "rewardRookie", "rewardAverage", "rewardExpert");
	}

}
