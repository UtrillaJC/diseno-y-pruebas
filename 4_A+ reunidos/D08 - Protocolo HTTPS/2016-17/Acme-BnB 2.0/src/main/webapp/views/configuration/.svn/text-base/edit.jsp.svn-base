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


<form:form action = "configuration/administrator/edit.do" modelAttribute = "configuration">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	
	<acme:textbox code="configuration.fee" path="fee"/>
	<acme:textbox code="configuration.vatNumber" path="vatNumber"/>
	
	<security:authorize access = "hasRole('ADMINISTRATOR')">
	
	<acme:submit name="save" code="configuration.save"/>
	
	<input type="button" name="cancel"
		value="<spring:message code="configuration.cancel" />"
		onclick="javascript: window.location.replace('configuration/administrator/showConfiguration.do')" />
		
	</security:authorize>
			
</form:form>