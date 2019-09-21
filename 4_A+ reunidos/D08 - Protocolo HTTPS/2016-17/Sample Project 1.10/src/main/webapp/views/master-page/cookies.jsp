<%--
 * cookies.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div id="cookies">
	<spring:message code="cookies.policy" /> <br />
	<spring:message code="cookies.policy.confirm" /> &nbsp;
	<a id="cookiesClose" href="javascript: void(0);"><spring:message code="cookies.policy.close" /></a>

</div>