<%--
 * registerNutritionist.jsp
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

<form:form action="security/registerNutritionist.do" modelAttribute="nutritionist" >
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.sentMessages" />
	<form:hidden path="userAccount.receivedMessages" />
	<form:hidden path="userAccount.folders" />
	<form:hidden path="curricula"/>
	<form:hidden path="comments"/>
	<form:hidden path="socialIdentity"/>
	<form:hidden path="followings"/>
	<form:hidden path="followers"/>

	<fieldset>
	
		<legend>
			<spring:message code="security.informationNut" />
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
			
	</fieldset>		
	<br />	
	
	<input type="submit" name="save"
			value="<spring:message code="security.save" />" />&nbsp;
	
	<input type="button" name="cancel"
		value="<spring:message code="security.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />	
	
</form:form>