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


<!-- The user/s who has/have authored more recipes. -->

<h3><spring:message code="administrator.dashboardUser.req4" /></h3>

<display:table name="usersAuthoredMoreRecipes" id="user" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code = "administrator.user.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "administrator.user.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "administrator.user.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>
	
</display:table>


<!-- A listing of users in descending order of popularity. -->

<h3><spring:message code="administrator.dashboardUser.req13" /></h3>

<display:table name="listUsersDescendingPopularity" id="user" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code = "administrator.user.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "administrator.user.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "administrator.user.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>
	
</display:table>

<!-- A listing of users in descending order regarding the average number of likes that their recipes get. -->

<h3><spring:message code="administrator.dashboardUser.req14" /></h3>

<display:table name="listUsersLikes" id="user" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code = "administrator.user.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "administrator.user.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "administrator.user.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>
	
</display:table>

<!-- A listing of users in descending order regarding the average number of dislikes that their recipes get. -->

<h3><spring:message code="administrator.dashboardUser.req15" /></h3>

<display:table name="listUsersDislikes" id="user" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code = "administrator.user.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "administrator.user.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "administrator.user.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>
	
</display:table>



<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


