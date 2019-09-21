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

<form:form action="ingredient/nutritionist/edit.do" modelAttribute="ingredient">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ingredientLines" />
	<form:hidden path="properties" />

	<form:label path="name">
		<spring:message code="ingredient.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	<br />
	
	<form:label path="description">
		<spring:message code="ingredient.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<br />
	
	<form:label path="picture">
		<spring:message code="ingredient.picture" />:
	</form:label>
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
	<br />
	
		<input type="submit" name="save" 
			value="<spring:message code="ingredient.save" />" />
			
		<input type="submit" name="delete"
			value="<spring:message code="ingredient.delete" />" 
			onclick="return confirm('<spring:message code = "ingredient.confirm.delete"/>')"/>
	
		<input type="button" name="cancel"
			value="<spring:message code="ingredient.cancel" />"
			onclick="javascript: window.location.replace('ingredient/nutritionist/list.do')" />
	

</form:form>