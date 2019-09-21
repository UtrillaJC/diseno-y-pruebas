<%--
 * dashboard.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!-- The ranking of companies according the number of campaigns that they have organised via their sponsors. -->

<div>
	<h3><spring:message code="administrator.dashboardSponsor.req22" /></h3>
	<div>
		<p>
			<jstl:out value="${rankingsOfCompaniesForCampaigns}"/>
		</p>
	</div>
</div>

<!-- The ranking of companies according their monthly bills. -->

<div>
	<h3><spring:message code="administrator.dashboardSponsor.req23" /></h3>
	<div>
		<p>
			<jstl:out value="${rankingsOfCompaniesForBills}"/>
		</p>
	</div>
</div>


<!-- The sponsors who have not managed a campaign for the last three months. -->

<h3><spring:message code="administrator.dashboardSponsor.req28" /></h3>

<display:table name="sponsorsWhoNotManagedCampaignLastThreeMonths" id="sponsor" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code = "administrator.sponsor.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "administrator.sponsor.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "administrator.sponsor.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>

	<spring:message code = "administrator.sponsor.nameCompany" var = "nameCompanyHeader" />
	<display:column property = "nameCompany" title = "${nameCompanyHeader}" sortable = "false"/>
	
</display:table>

<!-- The companies that have spent less than the average in their campaigns. -->

<div>
	<h3><spring:message code="administrator.dashboardSponsor.req29" /></h3>
	<div>
		<p>
			<jstl:out value="${companiesSpentLessAvgCampaigns}"/>
		</p>
	</div>
</div>

<!-- The companies that have spent at least 90% the maximum amount of money that a company has spent on a campaign. -->

<div>
	<h3><spring:message code="administrator.dashboardSponsor.req30" /></h3>
	<div>
		<p>
			<jstl:out value="${companiesSpentLeast90PerCent}"/>
		</p>
	</div>
</div>

<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


