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

<jstl:if test="${createChild}">
		<a><b><spring:message code="folder.parentName" />: <jstl:out
				value="${parentName}" /></b></a>
	<br />
	</jstl:if>

<form:form action="${requestURI}" modelAttribute="folder">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="systemFolder" />

	<form:hidden path="userAccount" />
	<form:hidden path="messages" />
	<jstl:if test="${createChild}">
		<form:hidden path="parent" />
	</jstl:if>

	<form:hidden path="children" />

	<jstl:if test="${!systemFolder}">
		<form:label path="name">
			<spring:message code="folder.name" />:
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />
	</jstl:if>
	<jstl:if test="${systemFolder}">
		<form:label path="name">
			<spring:message code="folder.name" />:
		</form:label>
		<jstl:out value="${folder.name}"/>
		<br />
	</jstl:if>


	<jstl:if test="${isNew}">

		<input type="submit" name="save"
			value="<spring:message code="folder.createFolder" />" />

	</jstl:if>

	<jstl:if test="${!isNew && isOwner}">

		<jstl:if test="${!systemFolder}">
			<input type="submit" name="save"
				value="<spring:message code="folder.save" />" />
		</jstl:if>

		<jstl:if test="${eraseable}">

			<input type="submit" name="delete"
				value="<spring:message code="folder.delete" />"
				onclick="return confirm('<spring:message code = "folder.confirm.delete"/>')" />
		</jstl:if>

		<jstl:if test="${!systemFolder}">
			<input type="button" name="createChildFolder"
				value="<spring:message code="folder.createChildFolder" />"
				onclick="javascript: window.location.replace('folder/actor/createChild.do?folderId=${folderId}')" />
		</jstl:if>

		<jstl:if test="${hasChildren}">
			<input type="button" name="viewChildren"
				value="<spring:message code="folder.viewChildren" />"
				onclick="javascript: window.location.replace('folder/actor/childrenList.do?folderId=${folderId}')" />
		</jstl:if>

	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="folder.cancel" />"
		onclick="javascript: window.location.replace('folder/actor/list.do')" />

</form:form>