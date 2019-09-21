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

<form:form action="valueAttribute/lessor/edit.do" modelAttribute="valueAttribute">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="property" />
		
		<acme:select items="${attributes}" itemLabel="name" code="valueAttribute.attribute" path="attribute"/>
		<acme:textbox path = "value" code = "valueAttribute.value" readonly = "false"/>
	
		<acme:submit name="save" code="valueAttribute.save" />
		<jstl:if test="${valueAttribute.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="valueAttribute.delete" />"
				onclick="return confirm('<spring:message code="valueAttribute.confirm.delete" />')" />&nbsp;
		</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="valueAttribute.cancel" />"
		onclick="history.go(-1)" />
		<br />
	
	</form:form>