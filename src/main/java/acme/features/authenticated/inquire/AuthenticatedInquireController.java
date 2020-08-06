
package acme.features.authenticated.inquire;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.inquires.Inquire;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/inquire/")
public class AuthenticatedInquireController extends AbstractController<Authenticated, Inquire> {

	// Internal state -----------------------------------

	@Autowired
	private AuthenticatedInquireListService	listService;

	@Autowired
	private AuthenticatedInquireShowService	showService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
