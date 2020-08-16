
package acme.features.administrator.overture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.overtures.Overture;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/overture/")
public class AdministratorOvertureController extends AbstractController<Administrator, Overture> {

	// Internal state -----------------------------------

	@Autowired
	private AdministratorOvertureUpdateService	updateService;

	@Autowired
	private AdministratorOvertureDeleteService	deleteService;

	@Autowired
	private AdministratorOvertureCreateService	createService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
