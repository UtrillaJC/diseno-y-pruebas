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


<!-- The minimum of the number of master classes per cook. -->

<div>
	<h3><spring:message code="administrator.dashboardMasterClass.req31" /></h3>
	<div>
		<p>
			<jstl:out value="${minMasterClassesCook}"/>
		</p>
	</div>
</div>

<!-- The maximum of the number of master classes per cook. -->

<div>
	<h3><spring:message code="administrator.dashboardMasterClass.req32" /></h3>
	<div>
		<p>
			<jstl:out value="${maxMasterClassesCook}"/>
		</p>
	</div>
</div>

<!-- The average of the number of master classes per cook. -->

<div>
	<h3><spring:message code="administrator.dashboardMasterClass.req33" /></h3>
	<div>
		<p>
			<jstl:out value="${avgMasterClassesCook}"/>
		</p>
	</div>
</div>

<!-- The standard deviation of the number of master classes per cook. -->

<div>
	<h3><spring:message code="administrator.dashboardMasterClass.req34" /></h3>
	<div>
		<p>
			<jstl:out value="${stddevMasterClassesCook}"/>
		</p>
	</div>
</div>


<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


