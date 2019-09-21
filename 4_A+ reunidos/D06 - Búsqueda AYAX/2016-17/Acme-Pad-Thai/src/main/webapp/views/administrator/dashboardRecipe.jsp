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


<!-- The minimum number of recipes per user. -->

<div>
	<h3><spring:message code="administrator.dashboardRecipe.req1" /></h3>
	<div>
		<p>
			<jstl:out value="${minRecipesUser}"/>
		</p>
	</div>
</div>


<!-- The average number of recipes per user. -->

<div>
	<h3><spring:message code="administrator.dashboardRecipe.req2" /></h3>
	<div>
		<p>
			<jstl:out value="${avgRecipesUser}"/>
		</p>
	</div>
</div>


<!-- The maximum number of recipes per user. -->

<div>
	<h3><spring:message code="administrator.dashboardRecipe.req3" /></h3>
	<div>
		<p>
			<jstl:out value="${maxRecipesUser}"/>
		</p>
	</div>
</div>

<!-- The minimum number of recipes that have qualified for a contest. -->

<div>
	<h3><spring:message code="administrator.dashboardRecipe.req5" /></h3>
	<div>
		<p>
			<jstl:out value="${minRecipesHaveQualifiedContest}"/>
		</p>
	</div>
</div>

<!-- The average number of recipes that have qualified for a contest. -->

<div>
	<h3><spring:message code="administrator.dashboardRecipe.req6" /></h3>
	<div>
		<p>
			<jstl:out value="${avgRecipesHaveQualifiedContest}"/>
		</p>
	</div>
</div>

<!-- The maximum number of recipes that have qualified for a contest. -->

<div>
	<h3><spring:message code="administrator.dashboardRecipe.req7" /></h3>
	<div>
		<p>
			<jstl:out value="${maxRecipesHaveQualifiedContest}"/>
		</p>
	</div>
</div>


<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


