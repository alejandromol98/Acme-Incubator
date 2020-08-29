
package acme.features.administrator.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.entities.storages.Storage;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBookkeeperCreateService implements AbstractCreateService<Administrator, Bookkeeper> {

	@Autowired
	AdministratorBookkeeperRepository repository;


	@Override
	public boolean authorise(final Request<Bookkeeper> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Bookkeeper> request, final Bookkeeper entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsabilityStatement");
	}

	@Override
	public Bookkeeper instantiate(final Request<Bookkeeper> request) {
		Bookkeeper result;

		result = new Bookkeeper();

		return result;
	}

	@Override
	public void validate(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Bookkeeper> request, final Bookkeeper entity) {
		this.repository.save(entity);

	}

	public void createFromStorage(final Storage storage) {
		assert storage != null;

		Bookkeeper bookkeeper = new Bookkeeper();
		bookkeeper.setFirmName(storage.getFirmName());
		bookkeeper.setResponsibilityStatement(storage.getResponsibilityStatement());
		bookkeeper.setUserAccount(storage.getAuthenticated().getUserAccount());

		this.repository.save(bookkeeper);

	}

}
