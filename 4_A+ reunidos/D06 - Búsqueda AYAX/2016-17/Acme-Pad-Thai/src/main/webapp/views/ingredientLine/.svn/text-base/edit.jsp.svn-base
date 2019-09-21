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

<form:form action = "ingredientLine/user/addIngredientLine.do" modelAttribute = "ingredientLine">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "recipe" />
	
	<form:label path="ingredient">
	<spring:message code="ingredientLine.ingredient"/>:
	</form:label>
	<form:select path="ingredient">
		<form:option label="----" value="0"></form:option>
		<form:options items="${ingredients}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors cssClass="error" path="ingredient"/>
	<br />
	<br />
	<form:label path = "multiplicity">
		<spring:message code = "ingredientLine.multiplicity" />:
	</form:label>
	<form:input path = "multiplicity"/>
	<form:errors cssClass = "error" path = "multiplicity"/>
	<br />
	<br />
	<form:label path="unit">
		<spring:message code="ingredientLine.unit" />:
	</form:label>
	<form:select id ="unit" path = "unit">
		<form:option value="0" label = "---------"/>
		<form:options items="${units}"  />
	</form:select>
	<form:errors cssClass = "error" path="unit" />
	<br/>
	<br/>
	<input type = "submit" name = "save" 
		value = " <spring:message code = "ingredientLine.add" />" />

	&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="ingredientLine.cancel" />"
		onclick="javascript: window.location.replace('contest/list.do')" />	
</form:form>