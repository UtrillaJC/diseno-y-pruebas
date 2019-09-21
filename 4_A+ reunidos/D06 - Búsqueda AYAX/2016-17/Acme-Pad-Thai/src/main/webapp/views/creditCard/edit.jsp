<%--
 * edit.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action = "creditCard/sponsor/edit.do" modelAttribute = "creditCard">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	
	<form:label path = "holderName">
		<spring:message code = "creditCard.holderName" />:
	</form:label>
	<form:input path = "holderName"/>
	<form:errors cssClass = "error" path = "holderName"/>
	<br />
	<br />
	
	<form:label path = "brandName">
		<spring:message code = "creditCard.brandName" />:
	</form:label>
	<form:input path = "brandName"/>
	<form:errors cssClass = "error" path = "brandName"/>
	<br />
	<br />
	
	<form:label path = "number">
		<spring:message code = "creditCard.number" />:
	</form:label>
	<form:input path = "number"/>
	<form:errors cssClass = "error" path = "number"/>
	<br />
	<br />
	
	<form:label path = "expirationMonth">
		<spring:message code = "creditCard.expirationMonth" />:
	</form:label>
	<form:input path = "expirationMonth"/>
	<form:errors cssClass = "error" path = "expirationMonth"/>
	<br />
	<br />
	
	<form:label path = "expirationYear">
		<spring:message code = "creditCard.expirationYear" />:
	</form:label>
	<form:input path = "expirationYear"/>
	<form:errors cssClass = "error" path = "expirationYear"/>
	<br />
	<br />
	
	<form:label path = "cvv">
		<spring:message code = "creditCard.cvv" />:
	</form:label>
	<form:input path = "cvv"/>
	<form:errors cssClass = "error" path = "cvv"/>
	<br />
	<br />
	
	
	<security:authorize access = "hasRole('SPONSOR')">
	
				<input type = "submit" name = "save" 
					value = " <spring:message code = "creditCard.save" />" />&nbsp;
					
				<input type="button" name="cancel"
					value="<spring:message code="creditCard.cancel" />"
					onclick="javascript: window.location.replace('creditCard/sponsor/view.do')" />
		
	</security:authorize>
			
</form:form>