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

<display:table name="applications" id="rowApplication" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >

	<spring:message code="application.service.type" var="serviceTypeHeader" />
	<display:column property="service.type" title="${serviceTypeHeader}"	sortable="false" />
	
	<spring:message code="application.service.title" var="serviceTitleHeader" />
	<display:column property="service.title" title="${serviceTitleHeader}"	sortable="false" />
	
	<spring:message code="application.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}"	sortable="false" />
	
	<spring:message code="application.customer" var="username" />
	<display:column property="customer.userAccount.username" title="${username}"	sortable="false" />
	
	
	<display:column>
	
	<jstl:if test="${rowApplication.status=='PENDING'}">
		<a  href="application/customer/accept.do?applicationId=${rowApplication.id}"><spring:message
					code="application.accept" /></a>
		/			
		<a href="application/customer/deny.do?applicationId=${rowApplication.id}"><spring:message
					code="application.deny" /></a>			
	</jstl:if>
	</display:column>
	
	
</display:table>


	<input type="button" name="cancel"
	value="<spring:message code="application.back" />"
	onclick="history.go(-1)" />


