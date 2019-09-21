<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${actionURI}" modelAttribute="manager">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="deactivated"/>
	<form:hidden path="suspicious"/>
	<!-- Relationship -->
	<form:hidden path="trips"/>
	<form:hidden path="survivalClasses"/>
	
	<form:label path="name"><spring:message code="manager.name"/></form:label>
	<form:input path="name"/>
	<form:errors path="name" cssClass="error"/>
	<br/>
	
	<form:label path="surname"><spring:message code="manager.surname"/></form:label>
	<form:input path="surname"/>
	<form:errors path="surname" cssClass="error"/>
	<br/>
	
	<form:label path="email"><spring:message code="manager.email"/></form:label>
	<form:input path="email"/>
	<form:errors path="email" cssClass="error"/>
	<br/>
	
	<form:label path="address"><spring:message code="manager.address"/></form:label>
	<form:input path="address"/>
	<form:errors path="address" cssClass="error"/>
	<br/>
	
	<form:label path="phone"><spring:message code="manager.phone"/></form:label>
	<form:input path="phone"/>
	<form:errors path="phone" cssClass="error"/>
	<br/>
	
	<form:label path="username"><spring:message code="manager.username"/></form:label>
	<form:input path="username"/>
	<form:errors path="username" cssClass="error"/>
	<br/>
	
	<form:label path="password"><spring:message code="manager.password"/></form:label>
	<form:input path="password"/>
	<form:errors path="password" cssClass="error"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="manager.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="manager.cancel" />" 
			onclick="javascript: relativeRedir('welcome/index.do');" />

</form:form>