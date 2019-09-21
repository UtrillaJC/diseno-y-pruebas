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

<display:table name="messages" id="row" pagesize="5" class="displaytag" keepStatus="false"  requestURI="${requestURI}" >

	<spring:message code="message.createdMoment" var="createdMomenHeader" />
	<display:column property="createdMoment" title="${createdMomenHeader}"	sortable="true"  format="{0,date,dd/MM/yyyy HH:mm }"/>
	
	<spring:message code="message.sender" var="senderHeader" />
	<display:column property="sender.name" title="${senderHeader}"	sortable="false" />
	
	<spring:message code="message.recipient" var="recipientHeader" />
	<display:column property="recipient.name" title="${recipientHeader}"	sortable="false" />
	
	<spring:message code="message.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"	sortable="false" />

	<jstl:if test="${ownerReply}">
		<display:column>
			<a href="message/actor/reply.do?messageId=${row.id}" ><spring:message code="message.reply" /></a>
		</display:column>
	</jstl:if>
	
	<jstl:if test="${ownerForward}">
		<display:column>
			<a href="message/actor/forward.do?messageId=${row.id}" ><spring:message code="message.forward" /></a>
		</display:column>
	</jstl:if>
	
	<display:column>
		<a href="message/actor/showMessage.do?messageId=${row.id}" ><spring:message code="message.showMessage" /></a>
	</display:column>
	
</display:table>


	<input type="button" name="create"
		value="<spring:message code="message.create" />"
		onclick="javascript: window.location.replace('message/actor/create.do')" />	


	<input type="button" name="cancel"
	value="<spring:message code="message.back" />"
	onclick="history.go(-1)" />


