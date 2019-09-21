<%--
 * register.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="auditor/administrator/register.do" modelAttribute="form">

	<acme:textbox code="register.username" path="username"/>

	<acme:password code="register.password" path="password"/>
	
	<acme:password code="register.verify.password" path="verifyPassword"/>
	
	<acme:textbox code="register.companyName" path="companyName"/>
	
	<acme:textbox code="register.name" path="name"/>
	
	<acme:textbox code="register.surname" path="surname"/>
	
	<acme:textbox code="register.email" path="email"/>
	
	<acme:textbox code="register.phone" path="phone"/>
	
	<acme:textbox code="register.picture" path="picture"/>
		
	<br />
		<acme:submit name="register" code="register.save"/>
	
		<acme:cancel url="${pageContext.request.contextPath}" code="register.cancel"/>
</form:form>