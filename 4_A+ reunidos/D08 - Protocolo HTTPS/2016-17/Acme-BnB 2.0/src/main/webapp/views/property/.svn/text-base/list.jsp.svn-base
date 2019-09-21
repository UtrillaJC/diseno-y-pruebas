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

<display:table name="properties" id="row" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >
		
	<spring:message code="property.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="property.ratePerDay" var="ratePerDayHeader" />
	<display:column property="ratePerDay" title="${ratePerDayHeader}"	sortable="false" />

	<spring:message code="property.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="false" />
	
	<spring:message code="property.requests" var="requestHeader"  />
	<display:column title="${requestHeader}" sortable="true">
		<jstl:out value="${row.requests.size()}"></jstl:out>
	</display:column>
	
	<display:column>
		<a href="lessor/showLessor.do?lessorId=${row.lessor.id}">
			<spring:message code="property.lessor" />
		</a>
	</display:column>

	<security:authorize access="hasRole('LESSOR')">
		<display:column>
			<a href="property/lessor/edit.do?propertyId=${row.id}"> <spring:message
					code="property.edit" />
			</a>
		</display:column>


		

		<security:authentication var="lessor" property="principal.id" />
			<jstl:if test="${row.lessor.userAccount.id==lessor}">
			<display:column>
				<a href="request/lessor/list.do?propertyId=${row.id}"> <spring:message
						code="property.requests" />
				</a>
			</display:column>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('TENANT') || hasRole('ADMINISTRATOR') || hasRole('AUDITOR') || isAnonymous()">

		<display:column>
			<a href="valueAttribute/list.do?propertyId=${row.id}"> 
						<spring:message code="property.showAttributes" />					
			</a>	
		</display:column>
	
	</security:authorize>

	
	<security:authorize access="hasRole('LESSOR')">

		<display:column>
			<a href="valueAttribute/lessor/list.do?propertyId=${row.id}"> 
						<spring:message code="property.showAttributes" />					
			</a>	
		</display:column>
	
	</security:authorize>


	<security:authorize access="isAuthenticated()">
		<display:column>
			<a href="audit/actor/list.do?propertyId=${row.id}"> 
						<spring:message code="property.audits" />					
			</a>	
		</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('TENANT')">
		<display:column>
			<a href="request/tenant/create.do?propertyId=${row.id}"><spring:message
					code="property.addRequest" /></a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('AUDITOR')">

		<display:column>
			<jstl:set var="contains" value="false" />
			<jstl:forEach var="item" items="${done}">
				<jstl:if test="${item eq row.id}">
					<jstl:set var="contains" value="true" />
				</jstl:if>
			</jstl:forEach>

			<jstl:if test="${!contains}">
				<a href="audit/auditor/create.do?propertyId=${row.id}"><spring:message
						code="property.newAudit" /></a>
			</jstl:if>

		</display:column>
		<display:column>
			<a href="audit/auditor/list.do?propertyId=${row.id}"><spring:message
					code="property.myAudit" /></a>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access = "hasRole('LESSOR')">
<input type="button" name="create"
  value="<spring:message code="property.create" />"
  onclick="javascript: window.location.replace('property/lessor/create.do')" />
</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="property.back" />"
	onclick="history.go(-1)" />


