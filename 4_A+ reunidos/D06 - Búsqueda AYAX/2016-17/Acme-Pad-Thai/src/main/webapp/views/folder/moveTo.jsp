<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Folder List -->

<a><spring:message code="folder.selectionText" /></a>

<display:table pagesize="5" class="displaytag" name="folders" id="row"
	requestURI="${requestURI }">

	<spring:message code="folder.name" var="nameHeader" />
	<display:column title="${nameHeader}">
		<a href="message/actor/moveTo.do?targetFolderId=${row.id}&messageId=${messageId}&sourceFolderId=${sourceFolderId}"><jstl:out
				value="${row.name}" /></a>
	</display:column>

</display:table>

<input type="button" name="cancel"
	value="<spring:message code="folder.cancel" />"
	onclick="javascript: window.location.replace('mailbox/actor/list.do')" />