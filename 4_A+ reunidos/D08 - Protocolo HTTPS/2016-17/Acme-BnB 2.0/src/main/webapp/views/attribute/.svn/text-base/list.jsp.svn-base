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

<display:table name="attributes" id="row" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >
		
	<spring:message code="attribute.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="false" />


	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<a href="attribute/administrator/edit.do?attributeId=${row.id}">
				<spring:message code="attribute.edit" />
			</a>
		</display:column>
	</security:authorize>

	
</display:table>

<security:authorize access = "hasRole('ADMINISTRATOR')">
<input type="button" name="create"
  value="<spring:message code="attribute.create" />"
  onclick="javascript: window.location.replace('attribute/administrator/create.do')" />
</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="attribute.back" />"
	onclick="history.go(-1)" />


