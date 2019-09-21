<%--
 * action-2.jsp
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

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form action="request/tenant/edit.do"
	modelAttribute="requestCreditCardForm">

	<form:hidden path="propertyId" />
	<form:hidden path="tenantId" />


	<fieldset>
		<legend>
			<spring:message code="request" />
		</legend>
		<acme:textbox code="request.checkInDate" path="checkInDate" />
		<acme:textbox code="request.checkOutDate" path="checkOutDate" />
		<form:label path="smoker" >
		<spring:message code="request.smoker"/>
		</form:label>
		<form:select path="smoker" class="form-control">
			<option value="False"><spring:message code="No" /></option>
			<option value="True"><spring:message code="Si" /></option>
		</form:select>

	</fieldset>
	<fieldset>
		<legend>
			<spring:message code="request.creditCard" />
		</legend>
		<acme:textbox code="creditCard.brandName" path="brandName" />
		<acme:textbox code="creditCard.holderName" path="holderName" />
		<acme:textbox code="creditCard.number" path="number" />
		<acme:textbox code="creditCard.expirationMonth" path="expirationMonth" />
		<acme:textbox code="creditCard.expirationYear" path="expirationYear" />
		<acme:textbox code="creditCard.cvvCode" path="cvvCode" />
	</fieldset>

	<acme:submit code="request.save" name="save" />

	<input type="button" name="cancel"
		value="<spring:message code="request.cancel" />"
		onclick="javascript: history.back()" />

</form:form>