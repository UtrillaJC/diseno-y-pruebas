<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
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

<form:form action = "fee/administrator/edit.do" modelAttribute = "fee">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	
	<form:label path = "amount">
		<spring:message code = "fee.amount" />:
	</form:label>
	<form:input path = "amount"/>
	<form:errors cssClass = "error" path = "amount"/>
	
	<security:authorize access = "hasRole('ADMINISTRATOR')">
	
	<input type = "submit" name = "save" 
		value = " <spring:message code = "fee.save" />" />
	&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="fee.cancel" />"
		onclick="javascript: window.location.replace('fee/administrator/showFee.do')" />
		
	</security:authorize>
			
</form:form>