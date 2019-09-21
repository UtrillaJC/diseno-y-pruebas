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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="audits" id="auditRow" pagesize="5"
	class="displaytag" keepStatus="false" requestURI="${requestURI}">

	<spring:message code="audit.moment" var="moment" />
	<display:column property="moment" title="${moment}" sortable="false" />

	<spring:message code="audit.text" var="text" />
	<display:column property="text" title="${text}" sortable="false" />

	<spring:message code="audit.attachments" var="attachments" />
	<display:column title="${attachments}" >
	<jstl:forEach items="${auditRow.attachments}" var="item">
	<a href="${item}"><jstl:out value="${item}"/></a>
	</jstl:forEach>
	</display:column>

	<display:column>
		<jstl:if test="${auditRow.draft}">
			<a href="audit/auditor/edit.do?id=${auditRow.id}"><spring:message
					code="edit" /></a>
		</jstl:if>
	</display:column>


</display:table>

<input type="button" value="<spring:message code="audit.back" />"
	onclick="javascript: history.back()" />