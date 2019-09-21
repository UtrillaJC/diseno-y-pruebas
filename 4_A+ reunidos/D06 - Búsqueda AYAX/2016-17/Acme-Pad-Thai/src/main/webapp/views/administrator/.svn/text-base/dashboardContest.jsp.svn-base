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


<!-- The contest/s for which more recipes has/have qualified. -->

<h3><spring:message code="administrator.dashboardContest.req8" /></h3>

<display:table name="contestswhichMoreRecipesQualified" id="contest" requestURI="${requestURI}" pagesize="5" class="displaytag">

<spring:message code="administrator.contest.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="administrator.contest.momentOpening" var="momentOpeningHeader" />
	<display:column property="momentOpening" title="${momentOpeningHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
	
	<spring:message code="administrator.contest.momentClosing" var="momentClosingHeader" />
	<display:column property="momentClosing" title="${momentClosingHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>

</display:table>
<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


