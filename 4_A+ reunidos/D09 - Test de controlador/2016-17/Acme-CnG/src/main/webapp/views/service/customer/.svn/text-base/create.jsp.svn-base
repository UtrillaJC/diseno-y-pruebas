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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action = "service/customer/create.do" modelAttribute = "serviceCoordinatesForm">

	<form:hidden path = "customerId" />

	<spring:message code="service.type" />
	<form:select path="type">
		<form:options items="${types}" />
	</form:select>

	<acme:textbox code="service.title" path="title"/>
	<acme:textarea code="service.description" path="description"/>
	<acme:textbox code="service.moment" path="moment" placeHolderCode="service.moment.ph"/>
	<acme:textbox code="service.originPlace" path="originPlace"/>
	<acme:textbox code="service.destinationPlace" path="destinationPlace" />
	<fieldset>
		<legend>
			<spring:message code="service.coordinates.origin" />
		</legend>

		<acme:textbox code="service.originCoordinates.latitude"
			path="originLatitude" />
		<acme:textbox code="service.originCoordinates.longitude"
			path="originLongitude" />
	</fieldset>
	
	<fieldset>
		<legend>
			<spring:message code="service.coordinates.destination" />
		</legend>
		<acme:textbox code="service.destinationCoordinates.latitude"
			path="destinationLatitude" />
		<acme:textbox code="service.destinationCoordinates.longitude"
			path="destinationLongitude" />
	</fieldset>
	
		<acme:submit name="save" code="service.save"/>
	
		<input type="button" name="cancel"
			value="<spring:message code="service.cancel" />"
			onclick="javascript: history.back()" />
		
			
</form:form>