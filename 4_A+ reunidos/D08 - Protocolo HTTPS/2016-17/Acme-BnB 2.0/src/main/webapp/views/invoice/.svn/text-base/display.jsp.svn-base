<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<h3>
		<spring:message code="invoice.moment" />:&nbsp; <jstl:out value="${invoice.moment}" />
	</h3>
	
	<h3>
		<spring:message code="invoice.tenantInformation" />:&nbsp; <jstl:out value="${invoice.tenantInformation}" />
	</h3>

	<h3>
		<spring:message code="invoice.details" />:&nbsp; <jstl:out value="${invoice.details}" />
	</h3>
	
	<h3>
		<spring:message code="invoice.totalAmountDue" />:&nbsp; <jstl:out value="${invoice.totalAmountDue}" />
	</h3>
	
	<h3>
		<spring:message code="invoice.creditCard" />:&nbsp; <jstl:out value="${invoice.creditCard.number}" />
	</h3>
</div>

<input type="button"
	value="<spring:message code="invoice.back" />"
	onclick="javascript: history.back()" />
		
