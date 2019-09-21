<%--
 * list.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
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

<display:table name="actors" id="actor" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >

	<spring:message code="actor.name" var="name" />
	<display:column property="name" title="${name}"	sortable="false" />

	
	<display:column>
		<a href="comment/actor/list.do?commentClassId=${actor.id}" ><spring:message code="actor.comments" /></a>
	</display:column>
	
	<display:column>
		<a href="comment/actor/create.do?commentClassId=${actor.id}" ><spring:message code="actor.comments.create" /></a>
	</display:column>
	
</display:table>


	<input type="button" name="cancel"
	value="<spring:message code="actor.back" />"
	onclick="history.go(-1)" />


