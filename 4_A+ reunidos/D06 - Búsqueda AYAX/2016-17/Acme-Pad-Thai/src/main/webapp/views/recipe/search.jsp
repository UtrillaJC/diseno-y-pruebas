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

<script type="text/javascript" src="scripts/ajax.js"></script>


<jstl:if test="${listAll == true}">
<form:form method="GET" id = "searchForm" action = "recipe/search.do" modelAttribute="search">

	<h4><spring:message code = "recipe.searchText" /></h4>

	<form:input path = "keyword" />
	<input type = "submit" name = "search"
		value = "<spring:message code = "recipe.search" />" />
		
</form:form>
</jstl:if>

<div id="tableContainer">
<jstl:if test="${!firstTime}">
	<display:table name = "recipes" id = "row" requestURI="recipe/searchForm.do" pagesize = "5" class = "displaytag" >
	
		<spring:message code = "recipe.title" var = "titleHeader" />
		<display:column property = "title" title = "${titleHeader}" sortable = "true"/>
	
		<spring:message code = "recipe.momentAuthored" var = "momentAuthoredHeader" />
		<display:column property = "momentAuthored" title = "${momentAuthoredHeader}" sortable = "false"/>
		
		<spring:message code = "recipe.momentUpdated" var = "momentUpdatedHeader" />
		<display:column property = "momentUpdated" title = "${momentUpdatedHeader}" sortable = "false"/>
		
		<spring:message code = "recipe.likes" var = "likesHeader" />
		<display:column property = "likes" title = "${likesHeader}" sortable = "false"/>
		
		<spring:message code = "recipe.dislikes" var = "dislikesHeader" />
		<display:column property = "dislikes" title = "${dislikesHeader}" sortable = "false"/>
		
	</display:table>
</jstl:if>	
</div>

