<%--
 * registerSponsor.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<script type="text/javascript" src="scripts/validatepassword.js"></script>

<form:form action="security/registerSponsor.do" modelAttribute="sponsor" >
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.sentMessages" />
	<form:hidden path="userAccount.receivedMessages" />
	<form:hidden path="userAccount.folders" />
	<form:hidden path="campaigns"/>
	<form:hidden path="socialIdentity"/>


	<fieldset>
	
		<legend>
			<spring:message code="security.informationSpo" />
		</legend>
		
		<form:label path="userAccount.username">
			<spring:message code="security.username" />:
		</form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username" />
		
		<br />
		<br />
		
		<form:label path="userAccount.password">
			<spring:message code="security.password" />:
		</form:label>
		<form:password path="userAccount.password" id="password"/>
		<form:errors cssClass="error" path="userAccount.password" />
		
		<br />
		<br />
		
		<form:label path="name">
			<spring:message code="security.name" />:
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		
		<br />
		<br />
		
		<form:label path="surname">
			<spring:message code="security.surname" />:
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />
		
		<br />
		<br />
			
		<form:label path="email">
			<spring:message code="security.email" />:
		</form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email" />
		
		<br />
		<br />
		
		<form:label path="phoneNumber">
			<spring:message code="security.phoneNumber" />:
		</form:label>
		<form:input path="phoneNumber" />
		<form:errors cssClass="error" path="phoneNumber" />
		
		<br />
		<br />
		
		<form:label path="address">
			<spring:message code="security.address" />:
		</form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />
		
		<br />
		<br />
		
		<form:label path="nameCompany">
			<spring:message code="security.nameCompany" />:
		</form:label>
		<form:input path="nameCompany" />
		<form:errors cssClass="error" path="nameCompany" />
		
	</fieldset>
	<br />
		
	<fieldset>
	
		<legend>
			<spring:message code="security.creditCard" />
		</legend>
		
		<form:label path="creditCard.holderName">
			<spring:message code="security.creditCard.holderName" />:
		</form:label>
		<form:input path="creditCard.holderName" />
		<form:errors cssClass="error" path="creditCard.holderName" />
		
		<br />
		<br />
		
		<form:label path="creditCard.brandName">
			<spring:message code="security.creditCard.brandName" />:
		</form:label>
		<form:input path="creditCard.brandName" />
		<form:errors cssClass="error" path="creditCard.brandName" />
			
		<br />
		<br />
		
		<form:label path="creditCard.number">
			<spring:message code="security.creditCard.number" />:
		</form:label>
		<form:input path="creditCard.number" />
		<form:errors cssClass="error" path="creditCard.number" />
			
		<br />
		<br />
		
				<form:label path="creditCard.expirationMonth">
			<spring:message code="security.creditCard.expirationMonth" />:
		</form:label>
		<form:input path="creditCard.expirationMonth" />
		<form:errors cssClass="error" path="creditCard.expirationMonth" />
			
		<br />
		<br />
		
				<form:label path="creditCard.expirationYear">
			<spring:message code="security.creditCard.expirationYear" />:
		</form:label>
		<form:input path="creditCard.expirationYear" />
		<form:errors cssClass="error" path="creditCard.expirationYear" />
			
		<br />
		<br />
		
		<form:label path="creditCard.cvv">
			<spring:message code="security.creditCard.cvv" />:
		</form:label>
		<form:input path="creditCard.cvv" />
		<form:errors cssClass="error" path="creditCard.cvv" />
		
	</fieldset>
	<br />	
	
	<input type="submit" name="save"
			value="<spring:message code="security.save" />" />&nbsp;
	
	<input type="button" name="cancel"
		value="<spring:message code="security.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />	
	
</form:form>