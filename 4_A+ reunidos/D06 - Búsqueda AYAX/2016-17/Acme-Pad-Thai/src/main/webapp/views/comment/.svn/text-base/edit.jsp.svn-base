<%--
 * edit.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    
<form:form action="comment/edit.do" modelAttribute="comment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="nameRecipe"/>
	<form:hidden path="person"/>


	<form:label path="title">
		<spring:message code="comment.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	<br />
	
	<form:label path="text">
		<spring:message code="comment.text"/>
	</form:label>
	<form:input path="text"/>
	<form:errors cssClass="error" path="text"/>
	<br />
	<br />
	
	<form:label path="stars">
		<spring:message code="comment.stars"/>
	</form:label>
	<form:input path="stars"/> <spring:message code="comment.starsHelp"/>
	<form:errors cssClass="error" path="stars"/>
	<br />
	<br />
	

	<input type="submit" name="save"
		value="<spring:message code="comment.create" />" />
		
	<input type="button" name="cancel"
		value="<spring:message code="comment.cancel" />"
		onclick="history.go(-1)" />
	<br />
	
</form:form>