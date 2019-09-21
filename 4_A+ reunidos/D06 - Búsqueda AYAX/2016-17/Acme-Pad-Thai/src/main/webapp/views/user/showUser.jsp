<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2 class = "user.name"><spring:message code = "user.name"/>: ${user.name}</h2>

<h2 class = "user.name"><spring:message code = "user.surname"/>: ${user.surname}</h2>

<a><spring:message code = "user.email"/>: ${user.email}</a>
<br />
<br />
<a><spring:message code = "user.phoneNumber"/>: ${user.phoneNumber}</a>
<br />
<br />
<a><spring:message code = "user.address"/>: ${user.address}</a>
<br />
<br />
	
<jstl:if test="${user.socialIdentity!=null}">
	<h3 class = "socialIdentity"><spring:message code = "socialIdentity.nick"/>: ${user.socialIdentity.nick}</h3>
	
	<a><spring:message code = "socialIdentity.name"/>: ${user.socialIdentity.name}</a>
	<br />
	<br />
	<a><spring:message code = "socialIdentity.link"/>: ${user.socialIdentity.link}</a>
	<br />
	<br />
	<a><spring:message code = "socialIdentity.picture"/>: ${user.socialIdentity.picture}</a>
	<br />
	<br />
</jstl:if>
	

<input type="button" name="cancel"
	value="<spring:message code="user.back" />"
	onclick="history.go(-1)" />
