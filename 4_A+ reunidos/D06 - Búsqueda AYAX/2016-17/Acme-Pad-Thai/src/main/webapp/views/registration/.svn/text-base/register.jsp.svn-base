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

<form:form action = "registration/user/register.do" modelAttribute = "registration">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "moment" />
	<form:hidden path = "contest" />
	
	<form:label path="recipe">
	<spring:message code="registration.recipe"/>:
	</form:label>
	<form:select path="recipe">
		<form:option label ="-----" value="0"></form:option>
		<form:options items="${recipes}" itemLabel="title" itemValue="id"/>
	</form:select>
	<form:errors cssClass="error" path="recipe"/>
	<br />
	<br />
	<input type = "submit" name = "save" 
		value = " <spring:message code = "registration.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="registrattion.cancel" />"
		onclick="history.go(-1)" />					
</form:form>