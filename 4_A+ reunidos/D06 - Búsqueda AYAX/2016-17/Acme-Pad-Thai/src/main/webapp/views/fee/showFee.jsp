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

		
	<!-- Listing table -->
	


<div>
	<h1>
		<jstl:out value="${row.amount}" />&nbsp;<spring:message code="fee.currency" /> 
	</h1>
</div>

<input type="button" name="modify" 
	value=" <spring:message code="fee.edit" />"
onclick="javascript: window.location.replace('fee/administrator/edit.do?feeId=${row.id}')" />
&nbsp;
<input type="button" name="cancel"
	value="<spring:message code="fee.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	