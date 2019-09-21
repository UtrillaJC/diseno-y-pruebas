<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Car'nGo Co., Inc." />
</div>

<br/>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.dashboard" /></a>
				<ul>
					<li class="arrow"></li>
						<li><a href="dashboard/administrator/list.do"><spring:message code="master.page.dashboardView" /></a></li>
				</ul>
			</li>		
			<li><a class="fNiv" href="banner/administrator/showBanner.do"><spring:message code="master.page.administrator.banner" /></a>
			</li>
			
				<li><a class="fNiv" href="comment/administrator/listAllComments.do"><spring:message code="master.page.administrator.allComments" /></a>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.Services" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="service/administrator/list.do"><spring:message code="master.page.ListServices" /></a></li>
					</ul>
			</li>
		
		</security:authorize>
			<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="service/customer/searchForm.do"><spring:message code="master.page.customer.search" /></a>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.customer.services" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="service/customer/create.do"><spring:message code="master.page.customer.service.create" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.customer.offers" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="service/customer/listOffers.do"><spring:message code="master.page.customer.allOffers" /></a></li>
					<li><a href="service/customer/listMyOffers.do"><spring:message code="master.page.customer.allMyOffers" /></a></li>
					<li><a href="service/customer/listOffersByNotCustomer.do"><spring:message code="master.page.customer.offers.notOwn" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.customer.requests" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="service/customer/listRequests.do"><spring:message code="master.page.customer.allRequests" /></a></li>
					<li><a href="service/customer/listMyRequests.do"><spring:message code="master.page.customer.allMyRequests" /></a></li>
					<li><a href="service/customer/listRequestsByNotCustomer.do"><spring:message code="master.page.customer.requests.notOwn" /></a></li>
				</ul>
			</li>

		</security:authorize>
		

		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		
			<li class="dropdown"><a href="javascript:void(0)"
										data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
												code="master.page.register" /></a>
										<ul class=dropdown-menu>

											<li class="arrow"></li>
											<li><a href="customer/register.do"><spring:message
														code="master.page.customer" /></a></li>
										</ul>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"><spring:message	code="master.page.actor.messages" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/actor/listReceivedMessages.do"><spring:message code="master.page.messages.listReceivedMessages" /></a></li>
					<li><a href="message/actor/listSendMessages.do"><spring:message code="master.page.messages.listSendMessages" /></a></li>
					<li><a href="message/actor/create.do"><spring:message code="master.page.messages.create" /></a></li>				
				</ul>
			</li>
			<li>
			<li><a class="fNiv"><spring:message	code="master.page.actor.actors" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/actor/list.do"><spring:message code="master.page.actor.actors.list" /></a></li>				
				</ul>
			</li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
	<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

