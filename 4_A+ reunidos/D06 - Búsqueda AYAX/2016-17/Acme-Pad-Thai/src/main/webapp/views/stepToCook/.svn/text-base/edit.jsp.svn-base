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

<form:form action = "recipe/user/addStepToCook.do" modelAttribute = "stepToCook">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "number" />
	<form:hidden path = "recipe" />
	
	<form:label path = "description">
		<spring:message code = "stepToCook.description" />:
	</form:label>
	<form:textarea path = "description"/>
	<form:errors cssClass = "error" path = "description"/>
	<br />
	<br />
	<form:label path = "picture">
		<spring:message code = "stepToCook.picture" />:
	</form:label>
	<form:input path = "picture"/>
	<form:errors cssClass = "error" path = "picture"/>
	<br />
	<br />	
	
	<form:label path = "hints">
		<spring:message code = "stepToCook.hints" />:
	</form:label>
	<form:textarea path = "hints"/>
	<form:errors cssClass = "error" path = "hints"/>
	<br />
	<br />	
	
	<input type = "submit" name = "save" 
		value = " <spring:message code = "stepToCook.save" />" />
	&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="stepToCook.cancel" />"
		onclick="history.go(-1)"/>
					
</form:form>