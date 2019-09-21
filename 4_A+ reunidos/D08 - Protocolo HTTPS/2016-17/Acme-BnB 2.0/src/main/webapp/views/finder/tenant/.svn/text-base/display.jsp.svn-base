<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<h3>
		<spring:message code="finder.destinationCity" />:&nbsp; <jstl:out value="${finderDisplay.destinationCity}" />
	</h3>
	
	<h3>
		<spring:message code="finder.minimumPrice" />:&nbsp; <jstl:out value="${finderDisplay.minimumPrice}" />
	</h3>

	<h3>
		<spring:message code="finder.maximumPrice" />:&nbsp; <jstl:out value="${finderDisplay.maximumPrice}" />
	</h3>
	
	<h3>
		<spring:message code="finder.keyword" />:&nbsp; <jstl:out value="${finderDisplay.keyword}" />
	</h3>
</div>

<a href="finder/tenant/edit.do"><spring:message code="finder.edit" /></a>&nbsp;
<a href="finder/tenant/search.do"><spring:message code="finder.search" /></a>&nbsp;


<input type="button"
	value="<spring:message code="finder.return" />"
	onclick="javascript: history.back()" />
		
<display:table name="properties" id="row" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >
		
	<spring:message code="property.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="false" />

	<spring:message code="property.ratePerDay" var="ratePerDayHeader" />
	<display:column property="ratePerDay" title="${ratePerDayHeader}"	sortable="false" />

	<spring:message code="property.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="false" />
	
	<spring:message code="property.requests" var="requestHeader"  />
	<display:column title="${requestHeader}" sortable="false">
		<jstl:out value="${row.requests.size()}"></jstl:out>
	</display:column>
	
	
</display:table>