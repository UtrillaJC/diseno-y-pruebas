<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Folder List -->

<jstl:if test="${children}">
	<a><b><spring:message code="folder.parentName" />: <jstl:out
				value="${parentName}" /></b></a>
	<br />
</jstl:if>

<display:table pagesize="5" class="displaytag" name="folders" id="row"
	requestURI="${requestURI }">

	<spring:message code="folder.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<display:column>
		<a href="folder/actor/edit.do?folderId=${row.id}"><spring:message
				code="folder.editFolder" /></a>
	</display:column>

</display:table>

<jstl:if test="${!children}">
<input type="button" name="create"
	value="<spring:message code="folder.createFolder" />"
	onclick="javascript: window.location.replace('folder/actor/create.do')" />

<input type="button" name="back"
	value="<spring:message code="folder.back" />"
	onclick="javascript: window.location.replace('mailbox/actor/list.do')" />
</jstl:if>

<jstl:if test="${children}">
<input type="button" name="create"
	value="<spring:message code="folder.createChildFolder" />"
	onclick="javascript: window.location.replace('folder/actor/createChild.do?folderId=${parentId}')" />

<input type="button" name="back"
	value="<spring:message code="folder.back" />"
	onclick="javascript: window.location.replace('folder/actor/edit.do?folderId=${parentId}')" />
</jstl:if>