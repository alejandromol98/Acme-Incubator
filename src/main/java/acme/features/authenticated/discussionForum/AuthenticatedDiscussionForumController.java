
package acme.features.authenticated.discussionForum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/discussion-forum/")
public class AuthenticatedDiscussionForumController extends AbstractController<Authenticated, DiscussionForum> {

	// Internal state -----------------------------------

	@Autowired
	private AuthenticatedDiscussionForumListMineService	listMineService;

	@Autowired
	private AuthenticatedDiscussionForumShowService		showService;


	// Constructors -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}

}
