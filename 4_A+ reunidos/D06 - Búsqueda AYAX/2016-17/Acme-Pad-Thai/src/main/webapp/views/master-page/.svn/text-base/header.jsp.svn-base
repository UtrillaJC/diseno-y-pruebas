<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="welcome/index.do"><img src="images/logo.png" alt="Acme Pad-Thai Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		
			<li><a class="fNiv"><spring:message code="master.page.recipe" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="recipe/searchForm.do"><spring:message code="master.page.recipeSearch" /></a></li>
	
					<li><a href="recipe/list.do"><spring:message code="master.page.recipeList" /></a></li>
			<security:authorize access="hasRole('USER')">
					<li><a href="recipe/user/list.do"><spring:message code="master.page.myRecipes" /></a></li>				
			</security:authorize>	
	
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message code="master.page.user" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/searchForm.do"><spring:message code="master.page.userSearch" /></a></li>
					<li><a href="user/list.do"><spring:message code="master.page.userList" /></a></li>		
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message code="master.page.contest" /></a>
				<ul>
						<li class="arrow"></li>
						<li><a href="contest/list.do"><spring:message code="master.page.contestList" /></a></li>		
					
					<security:authorize access="hasRole('USER')">
						<li><a href="contest/user/list.do"><spring:message code="master.page.contestActive" /></a></li>
					</security:authorize>
					
					<security:authorize access="hasRole('ADMINISTRATOR')">
						<li><a href="contest/administrator/create.do"><spring:message code="master.page.contest.create" /></a></li>
						<li><a href="contest/administrator/contestsFinished.do"><spring:message code="master.page.contest.finished" /></a></li>
					</security:authorize>	
				</ul>
			</li>
			<li><a class="fNiv"><spring:message code="master.page.masterClass" /></a>
				<ul>
						<li class="arrow"></li>
						<li><a href="masterClass/list.do"><spring:message code="master.page.masterClassList" /></a></li>		
					
					<security:authorize access="hasRole('ADMINISTRATOR')">
							<li><a href="masterClass/administrator/list-promoted.do"><spring:message code="master.page.masterClassListPromoted" /></a></li>    
							<li><a href="masterClass/administrator/list-demoted.do"><spring:message code="master.page.masterClassListDemoted" /></a></li>    
					</security:authorize>
					
					<security:authorize access="hasRole('USER')">
							<li><a href="masterClass/user/list-registered.do"><spring:message code="master.page.user.list.registered.masterClasses" /></a></li>					
							<li><a href="masterClass/user/list-not-registered.do"><spring:message code="master.page.user.list.not.registered.masterClasses" /></a></li>				
					</security:authorize>
					
					<security:authorize access="hasRole('COOK')">
							<li><a href="masterClass/cook/list.do"><spring:message code="master.page.myMasterClasses" /></a></li>				
					</security:authorize>
					
				</ul>
			</li>	
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
		
			<li><a class="fNiv"><spring:message
						code="master.page.bill" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="bill/administrator/list.do"><spring:message
								code="master.page.billUnPaidList" /></a></li>
					
				</ul>
			</li>
		
			<li><a class="fNiv"><spring:message code="master.page.category" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.category.list" /></a></li>
					<li><a href="category/administrator/create.do"><spring:message code="master.page.category.create" /></a></li>
						
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.administrator.dashboard" /></a>
			    <ul>
				
				   <li class="arrow"></li>
				   <li><a href="administrator/dashboardBill.do"><spring:message code="master.page.administrator.dashboardBill" /></a></li>
				   <li><a href="administrator/dashboardCampaign.do"><spring:message code="master.page.administrator.dashboardCampaign" /></a></li>
				   <li><a href="administrator/dashboardContest.do"><spring:message code="master.page.administrator.dashboardContest" /></a></li>
				   <li><a href="administrator/dashboardIngredient.do"><spring:message code="master.page.administrator.dashboardIngredient" /></a></li>
				   <li><a href="administrator/dashboardLearningMaterial.do"><spring:message code="master.page.administrator.dashboardLearningMaterial" /></a></li>
				   <li><a href="administrator/dashboardMasterClass.do"><spring:message code="master.page.administrator.dashboardMasterClass" /></a></li>
				   <li><a href="administrator/dashboardRecipe.do"><spring:message code="master.page.administrator.dashboardRecipe" /></a></li>
			       <li><a href="administrator/dashboardSponsor.do"><spring:message code="master.page.administrator.dashboardSponsor" /></a></li>
				   <li><a href="administrator/dashboardStepToCook.do"><spring:message code="master.page.administrator.dashboardStepToCook" /></a></li>
				   <li><a href="administrator/dashboardUser.do"><spring:message code="master.page.administrator.dashboardUser" /></a></li>
				   
			   </ul>
			</li>
			
			<li><a class="fNiv"><spring:message
						code="master.page.fee" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="fee/administrator/showFee.do"><spring:message
								code="master.page.modifyFee" /></a></li>		
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message
						code="master.page.spamTerm" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="spamTerm/administrator/showSpamTerm.do"><spring:message
								code="master.page.modifySpamTerm" /></a></li>		
				</ul>
			</li>
			
		</security:authorize>		
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.bill" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="bill/sponsor/list-paid.do"><spring:message
								code="master.page.bill.paid" /></a></li>
					<li><a href="bill/sponsor/list-unpaid.do"><spring:message
								code="master.page.bill.unpaid" /></a></li>
					
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message 
						code="master.page.campaign" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="campaign/sponsor/list.do"><spring:message
								code="master.page.campaignList" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message
						code="master.page.creditCard" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="creditCard/sponsor/view.do"><spring:message
								code="master.page.viewCreditCard" /></a></li>
					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('NUTRITIONIST') || hasRole('USER')">
			<li><a class="fNiv"><spring:message code="master.page.person" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="person/list-follow.do"><spring:message code="master.page.followings" /></a></li>					
					<li><a href="person/list-unfollow.do"><spring:message code="master.page.not.followings" /></a></li>
					<li><a href="recipe/list-recipes-follow.do"><spring:message code="master.page.recipesFollow" /></a></li>					
					
				</ul>
			</li>

			<li><a class="fNiv"><spring:message code="master.page.taste" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="recipe/list-without-tastes.do"><spring:message code="master.page.recipeWithoutTaste" /></a></li>
					<li><a href="recipe/list-likes.do"><spring:message code="master.page.recipeLikes" /></a></li>				
					<li><a href="recipe/list-dislikes.do"><spring:message code="master.page.recipeDislikes" /></a></li>
				</ul>
			</li> 
		</security:authorize>	
		
		<security:authorize access="hasRole('USER')">
				<li><a class="fNiv" href="registration/user/list.do">
					<spring:message code="master.page.registrations" /></a>		
				</li>
		</security:authorize>				
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
		
			<li><a class="fNiv"><spring:message 
						code="master.page.campaign" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="campaign/administrator/list.do"><spring:message
								code="master.page.campaignListFinished" /></a></li>
				</ul>
			</li>			
			<li><a class="fNiv"><spring:message	code="master.page.registration" /></a>
			<ul>
				<li class="arrow"></li>

				<li><a href="security/administrator/registerCook.do"><spring:message
								code="master.page.adminRegisterCook" /></a></li>		
			</ul>
			</li>
		</security:authorize>
	
		<security:authorize access="hasRole('NUTRITIONIST')">
			<li><a class="fNiv"><spring:message 
						code="master.page.ingredient" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="ingredient/nutritionist/list.do"><spring:message
									code="master.page.ingredientList" /></a></li>
					</ul>
			</li>	
			
			<li><a class="fNiv" href="curriculum/nutritionist/list.do">
				<spring:message code="master.page.listCurriculum" /></a>		
			</li>
			
			<li><a class="fNiv"><spring:message 
						code="master.page.property" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="property/nutritionist/list.do"><spring:message
									code="master.page.propertyList" /></a></li>
					</ul>
			</li>	
		</security:authorize>
				
		<security:authorize access="isAnonymous()">
			
			<li><a class="fNiv"><spring:message
						code="master.page.registration" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="security/registerUser.do"><spring:message
								code="master.page.registerUser" /></a></li>
					<li><a href="security/registerNutritionist.do"><spring:message
								code="master.page.registerNutritionist" /></a></li>
					<li><a href="security/registerSponsor.do"><spring:message
								code="master.page.registerSponsor" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>			
		</security:authorize>
	
	
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv" href="mailbox/actor/list.do"> <spring:message
				code="master.page.mailbox" /></a>
			</li>		
		
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>							
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>			
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="mailbox/actor/list.do"> <spring:message
				code="master.page.mailbox" /></a>
			</li>		
		
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>							
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>			
		</security:authorize>
		
			<security:authorize access="hasRole('NUTRITIONIST')">
			<li><a class="fNiv" href="mailbox/actor/list.do"> <spring:message
				code="master.page.mailbox" /></a>
			</li>		
			
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="nutritionist/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>							
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>			
		</security:authorize>
		
		<security:authorize access="hasRole('COOK')">
			<li><a class="fNiv" href="mailbox/actor/list.do"> <spring:message
				code="master.page.mailbox" /></a>
			</li>		
		
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="cook/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>							
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>			
		</security:authorize>
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv" href="mailbox/actor/list.do"> <spring:message
				code="master.page.mailbox" /></a>
			</li>		
		
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsor/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>							
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>			
		</security:authorize>
		
		
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

