
package acme.features.entrepreneur.monema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.monemas.Monema;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurMonemaShowService implements AbstractShowService<Entrepreneur, Monema> {

	// Internal State ---------------------------------------
	@Autowired
	EntrepreneurMonemaRepository repository;


	// AbstractListService interface ------------------------
	@Override
	public boolean authorise(final Request<Monema> request) {
		assert request != null;

		boolean result;
		int monemaId;
		Monema monema;
		Entrepreneur entrepreneur;
		Principal principal;

		monemaId = request.getModel().getInteger("id");
		monema = this.repository.findOneById(monemaId);
		entrepreneur = monema.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public Monema findOne(final Request<Monema> request) {
		assert request != null;

		Monema result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Monema> request, final Monema entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text");
	}

}
