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

<form:form action="lessor/register.do" modelAttribute="form">

	<acme:textbox code="register.username" path="username"/>

	<acme:password code="register.password" path="password"/>
	
	<acme:password code="register.verify.password" path="verifyPassword"/>
	
	<acme:textbox code="register.name" path="name"/>
	
	<acme:textbox code="register.surname" path="surname"/>
	
	<acme:textbox code="register.email" path="email"/>
	
	<acme:textbox code="register.phone" path="phone"/>
	
	<acme:textbox code="register.picture" path="picture"/>

<fieldset>
		<legend>
			<spring:message code="register.creditCard" />
		</legend>
		
	<acme:textbox code="register.creditCard.brandName" path="creditCard.brandName"/>
	
	<acme:textbox code="register.creditCard.holderName" path="creditCard.holderName"/>
	
	<acme:textbox code="register.creditCard.number" path="creditCard.number"/>
	
	<acme:textbox code="register.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
	
	<acme:textbox code="register.creditCard.expirationYear" path="creditCard.expirationYear"/>
	
	<acme:textbox code="register.creditCard.cvvCode" path="creditCard.cvvCode"/>
	<br />
	</fieldset>

	<br /><br />
	
	
	<form:checkbox path="contractAccepted" />
	<form:label path="contractAccepted"><spring:message code="register.contract.accepted1" />
		<a href="javascript: void(0);" id="termsLink" ><spring:message code="register.contract.accepted2" /></a>
		<spring:message code="register.contract.accepted3" /></form:label>
	<form:errors path="contractAccepted" cssClass="error" />
	<br />
					
	<br />
		<acme:submit name="register" code="register.save"/>
	
		<acme:cancel url="${pageContext.request.contextPath}" code="register.cancel"/>
</form:form>