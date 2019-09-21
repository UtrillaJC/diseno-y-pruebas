<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<form:form action="message/actor/edit.do" modelAttribute="men">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:hidden path="moment"/>
	<form:hidden path="subject"/>
	<form:hidden path="body"/>
	<form:hidden path="priority"/>
	
	<form:hidden path="sender"/>
	<form:hidden path="recipient"/>
	<form:hidden path="folder"/>
	
	<spring:message code="message.from" />
	:
	<jstl:out value="${men.sender.username}" />
	<br />
	
	<spring:message code="message.to" />
	:
	<jstl:out value="${men.recipient.username}" />
	<br />
	
	<spring:message code="message.date" />
	:
	<fmt:formatDate type="both" 
	            dateStyle="short" timeStyle="short" 
	            value="${men.moment}" /><br/>
	<br />
	
	<spring:message code="message.subject" />
	:
	<jstl:out value="${men.subject}" />
	<br />
	
	<spring:message code="message.body" />
	:
	<jstl:out value="${men.body}" />
	<br />
	<br />

	<input type = "submit" name = "delete" 
			value = " <spring:message code = "message.delete" />"
		onclick="return confirm('<spring:message code = "message.confirm.delete"/>')" />
	
	<input type="button" name="cancel"
		value="<spring:message code="message.back" />"
		onclick="javascript: window.location.replace('mailbox/actor/list.do')" />
	
</form:form>

<jstl:if test="${reply}">

	<input type="button" name="reply"
		value="<spring:message code="message.reply" />"
		onclick="javascript: window.location.replace('message/actor/reply.do?messageId=${men.id}')" />
</jstl:if>
