<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
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

<display:table name = "users" id = "row" requestURI = "user/list.do" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "user.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "user.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "user.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>
	
	
	<display:column>
		<a href="user/showUser.do?userId=${row.id}">
			<spring:message code="user.showUser" />
		</a>
	</display:column>
	
	<display:column>
		<a href="recipe/listByUser.do?userId=${row.id}">
			<spring:message code="user.listRecipes" />
		</a>
	</display:column>
	
</display:table>
