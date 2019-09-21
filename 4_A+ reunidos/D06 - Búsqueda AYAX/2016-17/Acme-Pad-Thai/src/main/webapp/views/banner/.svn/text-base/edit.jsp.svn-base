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

<form:form action = "banner/sponsor/edit.do" modelAttribute = "banner">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "campaign" />
	
	<form:label path = "description">
		<spring:message code = "banner.description" />:
	</form:label>
	<form:input path = "description"/>
	<form:errors cssClass = "error" path = "description"/>
	<br />
	<br />
	
	<form:label path = "maxNumDisplayed">
		<spring:message code = "banner.maxNumDisplayed" />:
	</form:label>
	<form:input path = "maxNumDisplayed"/>
	<form:errors cssClass = "error" path = "maxNumDisplayed"/>
	<br />
	<br />
	
	<form:label path = "numDisplayed">
		<spring:message code = "banner.numDisplayed" />:
	</form:label>
	<form:input path = "numDisplayed"/>
	<form:errors cssClass = "error" path = "numDisplayed"/>
	<br />
	<br />
	
	<input type = "submit" name = "save" 
		value = " <spring:message code = "banner.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="banner.cancel" />"
		onclick="javascript: window.location.replace('campaign/sponsor/list.do')" />
			
</form:form>