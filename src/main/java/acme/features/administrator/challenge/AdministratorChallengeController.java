
package acme.features.administrator.challenge;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.challenges.Challenge;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/challenge/")
public class AdministratorChallengeController extends AbstractController<Administrator, Challenge> {

	// Internal state -----------------------------------

	@Autowired
	private AdministratorChallengeUpdateService	updateService;

	@Autowired
	private AdministratorChallengeDeleteService	deleteService;

	@Autowired
	private AdministratorChallengeCreateService	createService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
