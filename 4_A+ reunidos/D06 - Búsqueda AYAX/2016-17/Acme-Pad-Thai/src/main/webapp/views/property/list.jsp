<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

		
	<!-- Listing table -->
	
<display:table name = "properties" id = "row" requestURI = "property/nutritionist/list.do" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "property.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "false"/>

	
	<security:authorize access="hasRole('NUTRITIONIST')">
		<display:column>
			<a href="property/nutritionist/edit.do?propertyId=${row.id}">
				<spring:message code="property.edit" />
			</a>
		</display:column>

	<jstl:if test="${addProperty}">	
		<display:column>	
			<a href="property/nutritionist/add.do?propertyId=${row.id}&ingredientId=${ingredientId}">
				<spring:message code="property.add" />
			</a>
		</display:column>
	</jstl:if>	
	</security:authorize>	

	
</display:table>

	<security:authorize access="hasRole('NUTRITIONIST')">
			<a href="property/nutritionist/create.do">
				<spring:message	code="property.create" /></a>
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="property.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	