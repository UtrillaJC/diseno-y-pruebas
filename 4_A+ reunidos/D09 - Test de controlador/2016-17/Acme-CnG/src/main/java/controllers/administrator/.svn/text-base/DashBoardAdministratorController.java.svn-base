
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Customer;
import services.CommentService;
import services.CustomerService;
import services.MessageService;
import services.ServiceService;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashBoardAdministratorController {

	@Autowired
	private ServiceService	serviceService;

	@Autowired
	private CustomerService	customerService;

	@Autowired
	private CommentService	commentService;

	@Autowired
	private MessageService	messageService;


	public DashBoardAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		result = new ModelAndView("dashboard/list");
		Double dashb1;
		Double dashb2;
		Double dashb3;
		Double dashb4;
		Double dashb5;
		Collection<Customer> dashb6;
		Collection<Customer> dashb7;
		Double dashb8;
		Double dashb9;
		Double dashb10;
		Double dashb11;
		Collection<Actor> dashb12;
		Double dashb13;
		Double dashb14;
		Double dashb15;
		Double dashb16;
		Double dashb17;
		Double dashb18;
		Collection<Actor> dashb19;
		Collection<Actor> dashb20;
		
		

		dashb1 = this.serviceService.ratioOffers();
		dashb2 = this.serviceService.ratioRequests();
		dashb3 = this.serviceService.avgServicesPerCustomer();
		dashb4 = this.serviceService.avgApplicationsPerOffer();
		dashb5 = this.serviceService.avgApplicationsPerRequest();
		dashb6 = this.customerService.customerMoreAppAcepted();
		dashb7 = this.customerService.customerMoreAppDenied();
		dashb8 = this.commentService.avgCommentPerActor();
		dashb9 = this.commentService.avgCommentPerOffer();
		dashb10 = this.commentService.avgCommentPerRequest();
		dashb11 = this.commentService.avgCommentPostedActor();
		dashb12 = this.commentService.postedActor10Avg();
		dashb13 = this.messageService.minMessagePerActor();
		dashb14 = this.messageService.maxMessagePerActor();
		dashb15 = this.messageService.avgMessagePerActor();
		dashb16 = this.messageService.minMessageReceivedPerActor();
		dashb17 = this.messageService.maxMessageReceivedPerActor();
		dashb18 = this.messageService.avgMessageReceivedPerActor();
		dashb19 = this.messageService.actorMoreSentMessage();
		dashb20 = this.messageService.actorMoreReceiverMessage();
		

		result.addObject("requestURI", "dashboard/administrator/list.do");
		result.addObject("dashb1", dashb1);
		result.addObject("dashb2", dashb2);
		result.addObject("dashb3", dashb3);
		result.addObject("dashb4", dashb4);
		result.addObject("dashb5", dashb5);
		result.addObject("dashb6", dashb6);
		result.addObject("dashb7", dashb7);
		result.addObject("dashb8", dashb8);
		result.addObject("dashb9", dashb9);
		result.addObject("dashb10", dashb10);
		result.addObject("dashb11", dashb11);
		result.addObject("dashb12", dashb12);
		result.addObject("dashb13", dashb13);
		result.addObject("dashb14", dashb14);
		result.addObject("dashb15", dashb15);
		result.addObject("dashb16", dashb16);
		result.addObject("dashb17", dashb17);
		result.addObject("dashb18", dashb18);
		result.addObject("dashb19", dashb19);
		result.addObject("dashb20", dashb20);

		return result;
	}

}
