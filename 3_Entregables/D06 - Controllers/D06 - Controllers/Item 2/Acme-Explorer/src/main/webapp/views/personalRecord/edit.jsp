<%-- edit.jsp de EducationRecord --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="personalRecord/ranger/edit.do" modelAttribute="personalRecord">

	<!-- Atributos ocultos -->
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<!-- Atributos ocultas de las relaciones -->
	<!-- No tiene ning�n atributo oculto-->
	
	<spring:message var="personalRecordFullName" code="personalRecord.fullName"/>
	<b><form:label path="fullName"/>${personalRecordFullName}:&nbsp;</b>
	<form:input path="fullName"/>
	<form:errors path="fullName" cssStyle="error"/>
	<br/>
	
	<spring:message var="personalRecordPhoto" code="personalRecord.photo"/>
	<b><form:label path="photo"/>${personalRecordPhoto}:&nbsp;</b>
	<form:input path="photo"/>
	<form:errors path="photo" cssStyle="error"/>
	<br/>
	
	<spring:message var="personalRecordEmail" code="personalRecord.email"/>
	<b><form:label path="email"/>${personalRecordEmail}:&nbsp;</b>
	<form:input path="email"/>
	<form:errors path="email" cssStyle="error"/>
	<br/>
	
	<spring:message var="personalRecordPhone" code="personalRecord.phone"/>
	<b><form:label path="phone"/>${personalRecordPhone}:&nbsp;</b>
	<form:input path="phone"/>
	<form:errors path="phone" cssStyle="error"/>
	<br/>

	<spring:message var="personalRecordLink" code="personalRecord.link"/>
	<b><form:label path="link"/>${personalRecordLink}:&nbsp;</b>
	<form:input path="link"/>
	<form:errors path="link" cssStyle="error"/>
	<br/>

	<!-- Botones del formulario -->
	<spring:message var="personalRecordSave" code="personalRecord.save"/>
	<input type="submit" name="save" value="${personalRecordSave}"/>
	
	<spring:message var="personalRecordCancel" code="personalRecord.cancel"/>
	<input type="button" name="cancel" value="${personalRecordCancel}" onclick="javascript:relativeRedir('${cancelURI}')"/>
</form:form>