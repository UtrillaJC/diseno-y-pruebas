<%--
 * list.jsp
 *
 * Copyright (C) 2012 Universidad de Sevilla
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

<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="comment/actor/create.do" modelAttribute="comment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="createdMoment" />
	<form:hidden path="author" />
	<form:hidden path="receiverId" />

	<acme:textbox path="title" code="comment.title" readonly="false"/>
	<acme:textarea path="text" code="comment.text" readonly="false"/>
	<acme:textbox path="stars" code="comment.stars" readonly="false"/>
	
	<acme:submit name="save" code="comment.save"/>


	<input type="button" name="cancel"
		value="<spring:message code="comment.cancel" />" 
		onclick="javascript: history.back()" />
	
	<br />


</form:form>
