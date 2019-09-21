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

<!-- The average of number of steps per recipe. -->

<div>
	<h3><spring:message code="administrator.dashboardStepToCook.req9" /></h3>
	<div>
		<p>
			<jstl:out value="${avgStepsRecipe}"/>
		</p>
	</div>
</div>

<!-- The standard deviation of number of steps per recipe. -->

<div>
	<h3><spring:message code="administrator.dashboardStepToCook.req10" /></h3>
	<div>
		<p>
			<jstl:out value="${stddevStepsRecipe}"/>
		</p>
	</div>
</div>


<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


