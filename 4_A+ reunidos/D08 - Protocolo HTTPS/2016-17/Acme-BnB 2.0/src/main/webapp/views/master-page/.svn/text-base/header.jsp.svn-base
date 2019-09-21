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
	<img src="images/logo.png" alt="Acme-BnB Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<li><a class="fNiv"><spring:message	code="master.page.dashboard" /></a>
				<ul>
					<li class="arrow"></li>
						<li><a href="dashboard/administrator/list.do"><spring:message code="master.page.dashboardGeneral" /></a></li>
						<li><a href="dashboard/administrator/listLessor.do"><spring:message code="master.page.dashboardByLessor" /></a></li>
				</ul>
			</li>		

		</security:authorize>
		
		
		<security:authorize access="hasRole('LESSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.lessor.amount" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="lessor/showAmount.do"><spring:message code="master.page.lessor.myAmount" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.lessor.comment" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="comment/lessor/create.do"><spring:message code="master.page.lessor.comment.create" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		
		<li><a class="fNiv"><spring:message code="master.page.property" /></a>
				<ul>
					<security:authorize access="!hasRole('AUDITOR')">
					<li><a href="property/list.do"><spring:message code="master.page.propertyList" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('AUDITOR')">
					<li><a href="property/auditor/list.do"><spring:message code="master.page.propertyList" /></a></li>
					</security:authorize>
				</ul>
		</li>
		

		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.configuration" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="configuration/administrator/showConfiguration.do"><spring:message
								code="master.page.modifyConfiguration" /></a></li>		
				</ul>
			</li>
		
		</security:authorize>
		
		
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator.attributes" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="attribute/administrator/list.do"><spring:message code="master.page.administrator.allAttributes" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.administrator.auditor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="auditor/administrator/register.do"><spring:message code="master.page.auditor.register" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		
		
		
		<security:authorize access="hasRole('LESSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.lessor.property" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="property/lessor/list.do"><spring:message code="master.page.lessor.myProperties" /></a></li>
					<li><a href="property/lessor/create.do"><spring:message code="master.page.lessor.create" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('TENANT')">
		<li><a class="fNiv"><spring:message	code="master.page.tenant.comment" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="comment/tenant/create.do"><spring:message code="master.page.tenant.comment.create" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.request" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="request/tenant/listOwn.do"><spring:message code="master.page.request.listOwn" /></a></li>			
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.finder" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="finder/tenant/display.do"><spring:message code="master.page.finder.display" /></a></li>			
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.invoice" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="invoice/tenant/list.do"><spring:message code="master.page.invoice.listOwn" /></a></li>			
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
											<li><a href="tenant/register.do"><spring:message
														code="master.page.tenant" /></a></li>
											<li><a href="lessor/register.do"><spring:message
														code="master.page.lessor" /></a></li>
										</ul>
			
		</security:authorize>
		
	
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/display.do"><spring:message code="master.page.myProfile" /></a></li>
				<security:authorize access="hasRole('TENANT')">
					<li><a href="tenant/edit.do"><spring:message code="master.page.myProfileEdit" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('LESSOR')">
					<li><a href="lessor/edit.do"><spring:message code="master.page.myProfileEdit" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('AUDITOR')">
					<li><a href="auditor/edit.do"><spring:message code="master.page.myProfileEdit" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('ADMINISTRATOR')">
					<li><a href="administrator/edit.do"><spring:message code="master.page.myProfileEdit" /></a></li>
				</security:authorize>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

