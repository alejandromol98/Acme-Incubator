
package acme.features.administrator.technologyRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/technology-record/")
public class AdministratorTechnologyRecordController extends AbstractController<Administrator, TechnologyRecord> {

	// Internal state -----------------------------------

	@Autowired
	private AdministratorTechnologyRecordUpdateService	updateService;

	@Autowired
	private AdministratorTechnologyRecordDeleteService	deleteService;

	@Autowired
	private AdministratorTechnologyRecordCreateService	createService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
