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
	<h3>
		<spring:message code="message.sender" />:&nbsp;<jstl:out value="${row.sender.name}" />
	</h3>
	<h3>
		<spring:message code="message.recipient" />:&nbsp;<jstl:out value="${row.recipient.name}" />
	</h3>

	<h3>
		<spring:message code="message.title" />:&nbsp;<jstl:out value="${row.title}" />
	</h3>
	
	<h3>
		<spring:message code="message.createdMoment" />:&nbsp;<jstl:out value="${row.createdMoment}" />
	</h3>
	
	<h4>
		<spring:message code="message.text" />:&nbsp;<jstl:out value="${row.text}" />
	</h4>
	

	<h3>
		<spring:message code="message.attachments" />:
			<jstl:forEach items="${row.attachments}" var="item">
			<a href="${item}"><jstl:out value="${item}"/></a>
			</jstl:forEach>			
	</h3>
	
</div>

<form:form action = "message/actor/create.do" modelAttribute = "row">
	
	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "sender" />
	<form:hidden path = "createdMoment" />
	
	<form:hidden path = "recipient" />
	<form:hidden path = "title" />
	<form:hidden path = "text" />
	<form:hidden path = "attachments" />

	<input type="submit" name="delete"
		value="<spring:message code="message.delete" />" 
		onclick="return confirm('<spring:message code = "message.confirm.delete"/>')"/>

	<input type="button" name="cancel"
	value="<spring:message code="message.cancel" />"
	onclick="history.go(-1)" />
	
</form:form>


	