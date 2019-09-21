<%--
 * index.jsp
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

<p>
	<spring:message code="welcome.greeting.prefix" />

	<security:authorize access="isAnonymous()">
		<spring:message code="welcome.greeting.middle" />
	</security:authorize>

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.username" />
	</security:authorize>
	<spring:message code="welcome.greeting.suffix" />
</p>

<p>
	<spring:message code="welcome.greeting.current.time" />
	${moment}
</p>

<h2 class="banner"><spring:message code="welcome.banners"/></h2>

<h3><jstl:out value="${row.description}" /></h3>







