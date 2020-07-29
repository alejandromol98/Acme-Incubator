
package acme.features.anonymous.molinaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.molinaBulletins.MolinaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/molina-bulletin/")
public class AnonymousMolinaBulletinController extends AbstractController<Anonymous, MolinaBulletin> {

	// Internal State --------------------------------------------
	@Autowired
	private AnonymousMolinaBulletinListService		listService;

	@Autowired
	private AnonymousMolinaBulletinCreateService	createService;


	// Constructor -----------------------------------------------
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
