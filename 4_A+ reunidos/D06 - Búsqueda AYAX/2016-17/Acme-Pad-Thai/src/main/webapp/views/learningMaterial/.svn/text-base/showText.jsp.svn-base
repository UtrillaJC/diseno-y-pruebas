<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<h2 class = "learningMaterial.title"><spring:message code = "learningMaterial.title"/>: ${learningMaterial.title}</h2>

<a><spring:message code = "learningMaterial.abstractText"/>: ${learningMaterial.abstractText}</a>
<br />
<br />
	
<a><spring:message code = "learningMaterial.attachments"/>:
	<jstl:forEach items="${attachments}"  var="row">
		<jstl:out value="${row}">
		
		</jstl:out>
	</jstl:forEach>
 </a>

<br />
<br />

<a><spring:message code = "learningMaterial.body"/>: ${learningMaterial.body}</a>

<br />
<br />

<input type="button" name="cancel"
	value="<spring:message code="user.back" />"
	onclick="history.go(-1)" />