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

<form:form action="${requestURI}" modelAttribute="message">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	
	<form:hidden path="sender" />
	<form:hidden path="folder" />
	
	<form:label path="priority">
		<spring:message code="message.priority" />:
	</form:label>
	<form:select id ="priority" path = "priority">
		<form:option value="0" label = "---------"/>
		<form:options items="${priorities}"  />
	</form:select>
	<form:errors cssClass = "error" path="priority" />
	<br/>
	
	<form:label path="recipient">
		<spring:message code="message.selectReceiver" />:
	</form:label>
	<form:select path = "recipient">
		<form:option value="0" label = "---------"/>
		<form:options items="${actors}" itemValue="id" itemLabel="username" />
	</form:select>
	<form:errors cssClass = "error" path="recipient" />
	<br/>
	
	<form:label path="subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />
	<br />

	<form:label path="body">
		<spring:message code="message.body" />:
	</form:label>
	<br />
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="message.send" />" />&nbsp;

	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: window.location.replace('mailbox/actor/list.do')" />

</form:form>