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

<display:table name="valueAttributes" id="row" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >
		
	<spring:message code="valueAttribute.attribute" var="attributeHeader" />
	<display:column property="attribute.name" title="${attributeHeader}" sortable="false" />

	<spring:message code="valueAttribute.value" var="valueHeader" />
	<display:column property="value" title="${attributeHeader}" sortable="false" />



	<security:authorize access="hasRole('LESSOR')">
	<jstl:if test="${owner}">
		<display:column>
			<a href="valueAttribute/lessor/edit.do?valueAttributeId=${row.id}">
				<spring:message code="valueAttribute.edit" />
			</a>
		</display:column>
	</jstl:if>
	</security:authorize>

	
</display:table>

<security:authorize access = "hasRole('LESSOR')">

	<jstl:if test="${owner}">

		<input type="button" name="add attribute"
		  value="<spring:message code="valueAttribute.addAttribute" />"
		  onclick="javascript: window.location.replace('valueAttribute/lessor/add.do?propertyId=${property.id}')" />
	</jstl:if>
</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="valueAttribute.back" />"
	onclick="history.go(-1)" />


