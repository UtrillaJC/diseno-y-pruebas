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

<form:form action="tenant/edit.do" modelAttribute="tenant">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount"/>
	<form:hidden path="socialIdentities"/>
	<form:hidden path="commentReceivers"/>
	<form:hidden path="commentCreates"/>
	<form:hidden path="requests"/>
	<form:hidden path="finder"/>
	
	<acme:textbox code="edit.name" path="name"/>
	
	<acme:textbox code="edit.surname" path="surname"/>
	
	<acme:textbox code="edit.email" path="email"/>
	
	<acme:textbox code="edit.phone" path="phone"/>
	
	<acme:textbox code="edit.picture" path="picture"/>

	<br />
					
	<br />
	
	<acme:submit name="save" code="edit.save"/>
	

	<input type="button" name="cancel"
		value="<spring:message code="edit.cancel" />" 
		onclick="javascript: location.replace('welcome/index.do')" />
		
		
</form:form>