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

<form:form action="property/lessor/edit.do" modelAttribute="property">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="lessor" />
		<form:hidden path="requests" />
		<form:hidden path="valueAttributes" />
		<form:hidden path="audits" />
		
		<acme:textbox path = "name" code = "property.name" readonly = "false"/>
		<acme:textbox path = "ratePerDay" code = "property.ratePerDay" readonly = "false"/>
		<acme:textarea path = "description" code = "property.description" readonly = "false"/>
		<acme:textarea path = "address" code = "property.address" readonly = "false"/>			
	
		<acme:submit name="save" code="property.save" />
		<jstl:if test="${property.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="property.delete" />"
				onclick="return confirm('<spring:message code="property.confirm.delete" />')" />&nbsp;
		</jstl:if>

		<input type="button" name="cancel"
			value="<spring:message code="property.cancel" />"
			onclick="javascript: window.location.replace('property/lessor/list.do')" />
		<br />
	
	</form:form>