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

<form:form action="${actionURI} " modelAttribute="explorer">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="deactivated"/>
	<form:hidden path="contacts"/>
	<!-- Relationship -->
	<form:hidden path="stories"/>
	<form:hidden path="applications"/>
	<form:hidden path="finder"/>
	<form:hidden path="survivalClasses"/>
	
	<form:label path="name"><spring:message code="explorer.name"/></form:label>
	<form:input path="name"/>
	<form:errors path="name" cssClass="error"/>
	<br/>
	
	<form:label path="surname"><spring:message code="explorer.surname"/></form:label>
	<form:input path="surname"/>
	<form:errors path="surname" cssClass="error"/>
	<br/>
	
	<form:label path="email"><spring:message code="explorer.email"/></form:label>
	<form:input path="email"/>
	<form:errors path="email" cssClass="error"/>
	<br/>
	
	<form:label path="address"><spring:message code="explorer.address"/></form:label>
	<form:input path="address"/>
	<form:errors path="address" cssClass="error"/>
	<br/>
	
	<form:label path="phone"><spring:message code="explorer.phone"/></form:label>
	<form:input path="phone"/>
	<form:errors path="phone" cssClass="error"/>
	<br/>
	
	<form:label path="username"><spring:message code="explorer.username"/></form:label>
	<form:input path="username"/>
	<form:errors path="username" cssClass="error"/>
	<br/>
	
	<form:label path="password"><spring:message code="explorer.password"/></form:label>
	<form:input path="password"/>
	<form:errors path="password" cssClass="error"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="explorer.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="explorer.cancel" />" 
			onclick="javascript: relativeRedir('welcome/index.do');" />

</form:form>

