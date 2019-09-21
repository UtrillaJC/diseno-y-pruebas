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

<form:form action = "${requestURI}" modelAttribute = "presentation">

	<form:hidden path = "id" />
	<form:hidden path = "version" />	
	<form:hidden path = "attachments" />
	<form:hidden path = "type" />
	<form:hidden path = "masterClass" />

	
	<form:label path = "title">
		<spring:message code = "learningMaterial.title" />:
	</form:label>
	<form:input path = "title"/>
	<form:errors cssClass = "error" path = "title"/>
	<br />
	<br />
	
	<form:label path = "abstractText">
		<spring:message code = "learningMaterial.abstractText" />:
	</form:label>
	<form:input path = "abstractText"/>
	<form:errors cssClass = "error" path = "abstractText"/>
	<br />
	<br />
	 

	<form:label path = "path">
		<spring:message code = "learningMaterial.path" />:
	</form:label>
	<form:input path = "path"/>
	<form:errors cssClass = "error" path = "path"/>
		

	<br />
	<br />
	
	<input type = "submit" name = "save" 
		value = " <spring:message code = "masterClass.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="masterClass.cancel" />"
		onclick="javascript: window.location.replace('masterClass/cook/list.do')" />
			
					
</form:form>