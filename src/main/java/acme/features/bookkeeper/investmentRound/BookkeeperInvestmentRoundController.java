
package acme.features.bookkeeper.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/investment-round/")
public class BookkeeperInvestmentRoundController extends AbstractController<Bookkeeper, InvestmentRound> {

	// Internal state -----------------------------------

	@Autowired
	private BookkeeperInvestmentRoundListMineService	listMineService;

	@Autowired
	private BookkeeperInvestmentRoundShowService		showService;

	@Autowired
	private BookkeeperInvestmentRoundListService		listService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}

}
