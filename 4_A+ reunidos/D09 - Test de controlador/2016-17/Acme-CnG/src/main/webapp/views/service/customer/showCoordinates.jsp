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


<form:form action = "service/customer/showCoordinates.do" modelAttribute = "service" >

	<fieldset>
		<legend>
			<spring:message code="service.coordinates.origin" />
		</legend>

		<acme:textbox code="service.originCoordinates.latitude"
			path="originCoordinates.latitude" readonly="true"/>
		<acme:textbox code="service.originCoordinates.longitude"
			path="originCoordinates.longitude" readonly="true" />
	</fieldset>
	
	<fieldset>
		<legend>
			<spring:message code="service.coordinates.destination" />
		</legend>
		<acme:textbox code="service.destinationCoordinates.latitude"
			path="destinationCoordinates.latitude" readonly="true" />
		<acme:textbox code="service.destinationCoordinates.longitude"
			path="destinationCoordinates.longitude" readonly="true"/>
	</fieldset>
	
		<input type="button" name="back"
			value="<spring:message code="service.back" />"
			onclick="javascript: history.back()" />
		
			
</form:form>