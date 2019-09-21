<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Folder List -->
<div id = mailbox>
<div id = mailboxFolderList>

<a><b><spring:message code="mailbox.folders" /></b></a><br/>

<display:table pagesize="5" class="displaytag" name="folders" id="row"
	requestURI="${requestURI }">

	<spring:message code="mailbox.name" var="nameHeader" />
	<display:column title="${nameHeader}">
			<a href="mailbox/actor/list2.do?folderId=${row.id}"><jstl:out value="${row.name}" /></a>
	</display:column>

</display:table><br/>

<input type="button" name="manageFolder"
	value="<spring:message code="mailbox.manageFolder" />"
	onclick="javascript: window.location.replace('folder/actor/list.do')" />
	<br/>
	
</div>
	
<!-- Messages List -->
<div id = mailboxMessageList>

<b><jstl:out value="${folderTitle}" />
<a><spring:message code="mailbox.messages" /></a></b><br/>

<display:table pagesize="5" class="displaytag" name="messages" id="row"
	requestURI="${requestURI }">

	<spring:message code="mailbox.sender" var="senderHeader" />
	<display:column property="sender.username" title="${senderHeader}" />

	<spring:message code="mailbox.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" />

	<spring:message code="mailbox.date" var="dateHeader" />
	<display:column property="moment" title="${dateHeader}"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />

	<display:column>
			<a href="message/actor/showMessage.do?messageId=${row.id}"><spring:message
					code="mailbox.viewMessage" /></a>
	</display:column>
	
	<display:column>
			<a href="folder/actor/moveTo.do?sourceFolderId=${sourceFolderId}&messageId=${row.id}"><spring:message
					code="mailbox.move" /></a>
	</display:column>

</display:table><br/>

<input type="button" name="compose"
	value="<spring:message code="mailbox.compose" />"
	onclick="javascript: window.location.replace('message/actor/create.do')" />
</div>

<div id = mailboxCommonButtons>
<input type="button" name="cancel"
	value="<spring:message code="message.back" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
</div>
</div>
