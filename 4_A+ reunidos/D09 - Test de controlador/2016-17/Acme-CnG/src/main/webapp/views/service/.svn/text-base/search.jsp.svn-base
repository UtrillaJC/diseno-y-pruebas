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

<form:form action = "service/customer/search.do">

	<h4><spring:message code = "service.searchText" /></h4>

	<input type = "text" name = "keyword" />
	<input type = "submit" name = "search"
		value = "<spring:message code = "service.search" />" />
		
</form:form>	

<jstl:if test="${!firstTime}">
<display:table name="services" id="row" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >

	<spring:message code="service.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"	sortable="false" />
	
	<spring:message code="service.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"	sortable="false" />
	
	<spring:message code="service.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"	sortable="false" format="{0,date,dd/MM/yyyy}"/>
	
	<spring:message code="service.originPlace" var="originPlaceHeader" />
	<display:column property="originPlace" title="${originPlaceHeader}"	sortable="false" />
	
	<spring:message code="service.destinationPlace" var="destinationPlaceHeader" />
	<display:column property="destinationPlace" title="${destinationPlaceHeader}"	sortable="false" />
	
	<display:column>
		<jstl:if test="${row.banned==true}">
			<font color=#FF0000><spring:message	code="service.banned"/></font>
		</jstl:if>
	</display:column>	
	
</display:table>
</jstl:if>


	<input type="button" name="cancel"
	value="<spring:message code="service.back" />"
	onclick="history.go(-1)" />


