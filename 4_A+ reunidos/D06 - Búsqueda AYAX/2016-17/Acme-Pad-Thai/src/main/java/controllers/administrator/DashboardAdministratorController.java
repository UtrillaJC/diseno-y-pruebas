/* AdministratorController.java
*
* Copyright (C) 2016 Universidad de Sevilla
* 
* The use of this project is hereby constrained to the conditions of the 
* TDG Licence, a copy of which you may download from 
* http://www.tdg-seville.info/License.html
* 
*/

package controllers.administrator;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.BillService;
import services.CampaignService;
import services.ContestService;
import services.IngredientService;
import services.LearningMaterialService;
import services.MasterClassService;
import services.RecipeService;
import services.SponsorService;
import services.StepToCookService;
import services.UserService;
import controllers.AbstractController;
import domain.Contest;
import domain.Cook;
import domain.Sponsor;
import domain.User;


@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController {
		
	
	// Services ---------------------------------------------------------------
		
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private StepToCookService stepToCookService;
	
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private CampaignService campaignService;

		
	// Constructors -----------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/dashboardRecipe", method = RequestMethod.GET)
	public ModelAndView listRecipe(){
		ModelAndView result;
		
		Double avgRecipesUser;
		Double minRecipesUser;
		Double maxRecipesUser;
		Double minRecipesHaveQualifiedContest;
		Double maxRecipesHaveQualifiedContest;
		Double avgRecipesHaveQualifiedContest;
		
		avgRecipesUser = recipeService.avgRecipesUser();
		minRecipesUser = recipeService.minRecipesUser();
		maxRecipesUser = recipeService.maxRecipesUser();
		minRecipesHaveQualifiedContest = recipeService.minRecipesHaveQualifiedContest();
		maxRecipesHaveQualifiedContest = recipeService.maxRecipesHaveQualifiedContest();
		avgRecipesHaveQualifiedContest = recipeService.avgRecipesHaveQualifiedContest();
		
		result = new ModelAndView("administrator/dashboardRecipe");
		
		result.addObject("avgRecipesUser", avgRecipesUser);
		result.addObject("minRecipesUser", minRecipesUser);
		result.addObject("maxRecipesUser", maxRecipesUser);
		result.addObject("minRecipesHaveQualifiedContest", minRecipesHaveQualifiedContest);
		result.addObject("maxRecipesHaveQualifiedContest", maxRecipesHaveQualifiedContest);
		result.addObject("avgRecipesHaveQualifiedContest", avgRecipesHaveQualifiedContest);
		
		return result;
	}
	
	
	@RequestMapping(value = "/dashboardUser", method = RequestMethod.GET)
	public ModelAndView listUser(){
		ModelAndView result;
		
		Collection<User> usersAuthoredMoreRecipes;
		Collection<User> listUsersDescendingPopularity;
		Collection<User> listUsersLikes;
		Collection<User> listUsersDislikes;

		usersAuthoredMoreRecipes = userService.usersAuthoredMoreRecipes();
		listUsersDescendingPopularity = userService.listUsersDescendingPopularity();
		listUsersLikes = userService.listUsersLikes();
		listUsersDislikes = userService.listUsersDislikes();
	
		result = new ModelAndView("administrator/dashboardUser");
		
		result.addObject("usersAuthoredMoreRecipes", usersAuthoredMoreRecipes);
		result.addObject("listUsersDescendingPopularity", listUsersDescendingPopularity);
		result.addObject("listUsersLikes", listUsersLikes);
		result.addObject("listUsersDislikes", listUsersDislikes);

		
		return result;
	}
	
	@RequestMapping(value = "/dashboardBill", method = RequestMethod.GET)
	public ModelAndView listBill(){
		ModelAndView result;
		
		Double avgBillsPaid;
		Double avgBillsNotPaid;
		Double stddevBillsPaid;
		Double stddevBillsNotPaid;

		avgBillsPaid = billService.avgBillsPaid();
		avgBillsNotPaid = billService.avgBillsNotPaid();
		stddevBillsPaid = billService.stddevBillsPaid();
		stddevBillsNotPaid = billService.stddevBillsNotPaid();
		
		result = new ModelAndView("administrator/dashboardBill");
		
		result.addObject("avgBillsPaid", avgBillsPaid);
		result.addObject("avgBillsNotPaid", avgBillsNotPaid);
		result.addObject("stddevBillsPaid", stddevBillsPaid);
		result.addObject("stddevBillsNotPaid", stddevBillsNotPaid);
		
		return result;
	}
	
	@RequestMapping(value = "/dashboardContest", method = RequestMethod.GET)
	public ModelAndView listContest(){
		ModelAndView result;
		
		Collection<Contest> contestswhichMoreRecipesQualified;

		contestswhichMoreRecipesQualified = contestService.contestswhichMoreRecipesQualified();
		
		result = new ModelAndView("administrator/dashboardContest");
		
		result.addObject("contestswhichMoreRecipesQualified", contestswhichMoreRecipesQualified);
		
		return result;
	}
	
	@RequestMapping(value = "/dashboardMasterClass", method = RequestMethod.GET)
	public ModelAndView listMasterClass(){
		ModelAndView result;
		
		Double minMasterClassesCook;
		Double maxMasterClassesCook;
		Double avgMasterClassesCook;
		Double stddevMasterClassesCook;

		minMasterClassesCook = masterClassService.minMasterClassesCook();
		maxMasterClassesCook = masterClassService.maxMasterClassesCook();
		avgMasterClassesCook = masterClassService.avgMasterClassesCook();
		stddevMasterClassesCook = masterClassService.stddevMasterClassesCook();
		
		result = new ModelAndView("administrator/dashboardMasterClass");
		
		result.addObject("minMasterClassesCook", minMasterClassesCook);
		result.addObject("maxMasterClassesCook", maxMasterClassesCook);
		result.addObject("avgMasterClassesCook", avgMasterClassesCook);
		result.addObject("stddevMasterClassesCook", stddevMasterClassesCook);	
		
		return result;
	}
	
	@RequestMapping(value = "/dashboardSponsor", method = RequestMethod.GET)
	public ModelAndView listSponsor(){
		ModelAndView result;
		
		Collection<String> rankingsOfCompaniesForCampaigns;
		Collection<String> rankingsOfCompaniesForBills;
		Collection<Sponsor> sponsorsWhoNotManagedCampaignLastThreeMonths;
		Collection<String> companiesSpentLessAvgCampaigns;
		Collection<String> companiesSpentLeast90PerCent;

		rankingsOfCompaniesForCampaigns = sponsorService.rankingsOfCompaniesForCampaigns();
		rankingsOfCompaniesForBills = sponsorService.rankingsOfCompaniesForBills();
		sponsorsWhoNotManagedCampaignLastThreeMonths = sponsorService.sponsorsWhoNotManagedCampaignLastThreeMonths();
		companiesSpentLessAvgCampaigns = sponsorService.companiesSpentLessAvgCampaigns();
		companiesSpentLeast90PerCent = sponsorService.companiesSpentLeast90PerCent();
		
		result = new ModelAndView("administrator/dashboardSponsor");
		
		result.addObject("rankingsOfCompaniesForCampaigns", rankingsOfCompaniesForCampaigns);
		result.addObject("rankingsOfCompaniesForBills", rankingsOfCompaniesForBills);
		result.addObject("sponsorsWhoNotManagedCampaignLastThreeMonths", sponsorsWhoNotManagedCampaignLastThreeMonths);
		result.addObject("companiesSpentLessAvgCampaigns", companiesSpentLessAvgCampaigns);
		result.addObject("companiesSpentLeast90PerCent", companiesSpentLeast90PerCent);
		
		return result;
	}
	
	@RequestMapping(value = "/dashboardStepToCook", method = RequestMethod.GET)
	public ModelAndView listStepToCook(){
		ModelAndView result;
		
		Double avgStepsRecipe;
		Double stddevStepsRecipe; 

		avgStepsRecipe = stepToCookService.avgStepsRecipe();
		stddevStepsRecipe = stepToCookService.stddevStepsRecipe();	
		
		result = new ModelAndView("administrator/dashboardStepToCook");
		
		result.addObject("avgStepsRecipe", avgStepsRecipe);
		result.addObject("stddevStepsRecipe", stddevStepsRecipe);
		
		return result;
	}
	
	@RequestMapping(value = "/dashboardIngredient", method = RequestMethod.GET)
	public ModelAndView listIngredient(){
		ModelAndView result;
		
		Double stddevIngredientsRecipe;
		Double avgIngredientsRecipe;

		stddevIngredientsRecipe = ingredientService.stddevIngredientsRecipe();
		avgIngredientsRecipe = ingredientService.avgIngredientsRecipe();
		
		result = new ModelAndView("administrator/dashboardIngredient");
		
		result.addObject("avgIngredientsRecipe", avgIngredientsRecipe);
		result.addObject("stddevIngredientsRecipe", stddevIngredientsRecipe);
		
		return result;
	}
	
	@RequestMapping(value = "/dashboardLearningMaterial", method = RequestMethod.GET)
	public ModelAndView listLearningMaterial(){
		ModelAndView result;
		
		Double avgNumberLearningMaterialsGroupedLearningMaterials;
		Integer numberOfMasterClassPromoted;
		Collection<Cook> listOfCookOrderByNumMAsterClassPromoted;
		Double avgNumberPromotedPerCook;
		Double avgNumberDemotedPerCook;	

		avgNumberLearningMaterialsGroupedLearningMaterials = learningMaterialService.avgNumberLearningMaterialsGroupedLearningMaterials();
		numberOfMasterClassPromoted = learningMaterialService.numberOfMasterClassPromoted();
		listOfCookOrderByNumMAsterClassPromoted = learningMaterialService.listOfCookOrderByNumMasterClassPromoted();
		avgNumberPromotedPerCook = learningMaterialService.avgNumberPromotedPerCook();
		avgNumberDemotedPerCook =  learningMaterialService.avgNumberDemotedPerCook();
		
		result = new ModelAndView("administrator/dashboardLearningMaterial");
		
		result.addObject("avgNumberLearningMaterialsGroupedLearningMaterials", avgNumberLearningMaterialsGroupedLearningMaterials);
		result.addObject("numberOfMasterClassPromoted", numberOfMasterClassPromoted);
		result.addObject("listOfCookOrderByNumMAsterClassPromoted", listOfCookOrderByNumMAsterClassPromoted);
		result.addObject("avgNumberPromotedPerCook", avgNumberPromotedPerCook);
		result.addObject("avgNumberDemotedPerCook", avgNumberDemotedPerCook);
					
		return result;
	}
	
	@RequestMapping(value = "/dashboardCampaign", method = RequestMethod.GET)
	public ModelAndView listCampaign(){
		ModelAndView result;
		
		Double findMinNumber;
		Double findAvgNumber;
		Double findMaxNumber;
		Double findMinNumberActiveCampaign;
		Double findAvgNumberActiveCampaign;
		Double findMaxNumberActiveCampaign;

		findMinNumber = campaignService.findMinNumber();
		findAvgNumber = campaignService.findAvgNumber();
		findMaxNumber = campaignService.findMaxNumber();
		findMinNumberActiveCampaign = campaignService.findMinNumberActiveCampaign();
		findAvgNumberActiveCampaign =  campaignService.findAvgNumberActiveCampaign();
		findMaxNumberActiveCampaign =  campaignService.findMaxNumberActiveCampaign();

		result = new ModelAndView("administrator/dashboardCampaign");
		
		result.addObject("findMinNumber", findMinNumber);
		result.addObject("findAvgNumber", findAvgNumber);
		result.addObject("findMaxNumber", findMaxNumber);
		result.addObject("findMinNumberActiveCampaign", findMinNumberActiveCampaign);
		result.addObject("findAvgNumberActiveCampaign", findAvgNumberActiveCampaign);
		result.addObject("findMaxNumberActiveCampaign", findMaxNumberActiveCampaign);
					
		return result;
	}
}