
package acme.features.authenticated.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.storages.Storage;
import acme.entities.storages.StorageStatus;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedStorageCreateService implements AbstractCreateService<Authenticated, Storage> {

	@Autowired
	private AuthenticatedStorageRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Storage> request) {
		assert request != null;

		boolean result;
		int numberOfAuthenticated;
		Principal principal = request.getPrincipal();

		numberOfAuthenticated = this.repository.findNumberOfAuditorByUserAccountId(principal.getAccountId());
		result = numberOfAuthenticated < 1;
		return result;
	}

	@Override
	public void bind(final Request<Storage> request, final Storage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "authenticated");

	}

	@Override
	public void unbind(final Request<Storage> request, final Storage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement", "status");

	}

	@Override
	public Storage instantiate(final Request<Storage> request) {
		assert request != null;

		Storage result;
		Principal principal;
		int userAccountId;
		Authenticated authenticated;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		authenticated = this.repository.findAuthenticatedByUserAccountId(userAccountId);

		result = new Storage();
		result.setAuthenticated(authenticated);
		result.setStatus(StorageStatus.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<Storage> request, final Storage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<Customisation> customisations = (List<Customisation>) this.customisationRepository.findManyAll();
		Customisation customisation = customisations.get(0);
		String[] spam = customisation.getSpamwords().split(",");

		//Validate Firm Name spam
		if (!errors.hasErrors("firmName")) {
			String firmName = entity.getFirmName();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(firmName.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (firmName.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "firmName", "authenticated.storage.form.valid.firmName");
		}

		//Validate responsibility statement spam
		if (!errors.hasErrors("responsibilityStatement")) {
			String responsibilityStatement = entity.getResponsibilityStatement();
			Integer n = 0;

			Double threshold;

			threshold = Double.valueOf(responsibilityStatement.split(" ").length) * customisation.getThreshold() / 100;

			for (String s : spam) {
				if (responsibilityStatement.toLowerCase().contains(s.toLowerCase())) {
					n++;
				}
			}

			errors.state(request, n <= threshold, "responsibilityStatement", "authenticated.storage.form.valid.responsibilityStatement");
		}

		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		errors.state(request, !(this.repository.numberOfStoragePendingByUserAccountId(userAccountId) > 0), "responsibilityStatement", "authenticated.storage.error.pending");

	}

	@Override
	public void create(final Request<Storage> request, final Storage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Storage> request, final Response<Storage> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
