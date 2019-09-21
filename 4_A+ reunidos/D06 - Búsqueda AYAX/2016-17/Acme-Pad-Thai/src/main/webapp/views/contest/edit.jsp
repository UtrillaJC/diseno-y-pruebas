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

<form:form action = "contest/administrator/edit.do" modelAttribute = "contest">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "registrations" />
	<form:hidden path = "winners" />
	
	<jstl:if test="${contest.id != 0 }">
		<form:label path = "title">
			<spring:message code = "contest.title" />:
		</form:label>
		<form:input path = "title" readonly="true"/>
		<form:errors cssClass = "error" path = "title"/>
		<br/>
		<br/>
		<form:label path = "momentOpening">
			<spring:message code = "contest.momentOpening" />:
		</form:label>
		<form:input path = "momentOpening" readonly="true"/>
		<form:errors cssClass = "error" path = "momentOpening"/>
	</jstl:if>

	<jstl:if test="${contest.id == 0 }">
		<form:label path = "title">
			<spring:message code = "contest.title" />:
		</form:label>
		<form:input path = "title"/>
		<form:errors cssClass = "error" path = "title"/>
		<br/>
		<br/>
		<form:label path = "momentOpening">
			<spring:message code = "contest.momentOpening" />:
		</form:label>
		<form:input path = "momentOpening" />
		<form:errors cssClass = "error" path = "momentOpening"/>
	</jstl:if>

	<br/>
	<br/>
	<form:label path = "momentClosing">
		<spring:message code = "contest.momentClosing" />:
	</form:label>
	<form:input path = "momentClosing"/>
	<form:errors cssClass = "error" path = "momentClosing"/>
	<br/>
	<br/>
	<input type = "submit" name = "save" 
		value = " <spring:message code = "contest.save" />" />
	&nbsp;
	<jstl:if test="${contest.id != 0 }">
	<input type = "submit" name = "delete" 
		value = " <spring:message code = "contest.delete" />" 
		onclick="return confirm('<spring:message code = "contest.confirm.delete"/>')" />
	</jstl:if>
	&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="contest.cancel" />"
		onclick="javascript: window.location.replace('contest/list.do')" />	
</form:form>