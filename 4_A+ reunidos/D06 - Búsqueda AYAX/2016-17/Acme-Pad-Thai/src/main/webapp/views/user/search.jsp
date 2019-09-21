<%--
 * search.jsp
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

<form:form action = "user/search.do">

	<h4><spring:message code = "user.searchText" /></h4>

	<input type = "text" name = "keyword" />
	<input type = "submit" name = "search"
		value = "<spring:message code = "user.search" />" />
		
</form:form>

<jstl:if test="${!firstTime}">
	<display:table name = "users" id = "row" requestURI="user/searchForm.do" pagesize = "10" class = "displaytag" >
	
		<spring:message code = "user.name" var = "nameHeader" />
		<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
		<spring:message code = "user.surname" var = "surnameHeader" />
		<display:column property = "surname" title = "${surnameHeader}" sortable = "true"/>
		
		
	</display:table>
</jstl:if>	
