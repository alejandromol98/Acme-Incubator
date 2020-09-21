
package acme.features.entrepreneur.monema;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.monemas.Monema;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/monema/")
public class EntrepreneurMonemaController extends AbstractController<Entrepreneur, Monema> {

	// Internal state -----------------------------------

	@Autowired
	private EntrepreneurMonemaShowService	showService;

	@Autowired
	private EntrepreneurMonemaCreateService	createService;

	@Autowired
	private EntrepreneurMonemaListService	listService;

	@Autowired
	private EntrepreneurMonemaDeleteService	deleteService;

	@Autowired
	private EntrepreneurMonemaUpdateService	updateService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
