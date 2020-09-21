
package acme.features.investor.monema;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.monemas.Monema;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/monema/")
public class InvestorMonemaController extends AbstractController<Investor, Monema> {

	// Internal state -----------------------------------

	@Autowired
	private InvestorMonemaShowService	showService;

	@Autowired
	private InvestorMonemaListService	listService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
