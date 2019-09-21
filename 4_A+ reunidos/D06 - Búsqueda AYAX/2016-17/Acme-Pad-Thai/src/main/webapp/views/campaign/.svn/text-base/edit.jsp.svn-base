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

<form:form action = "campaign/sponsor/edit.do" modelAttribute = "campaign">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	<form:hidden path = "sponsor" />
	<form:hidden path = "isStar" />
	<form:hidden path = "bills" />
	<form:hidden path = "banners" />
	
	
	
	<form:label path = "dateStart">
		<spring:message code = "campaign.dateStart" />:
	</form:label>
	<form:input path = "dateStart"/>
	<form:errors cssClass = "error" path = "dateStart"/>
	<br />
	<br />
	
	<form:label path = "dateEnd">
		<spring:message code = "campaign.dateEnd" />:
	</form:label>
	<form:input path = "dateEnd"/>
	<form:errors cssClass = "error" path = "dateEnd"/>
	<br />
	<br />
	
	<input type = "submit" name = "save" 
		value = " <spring:message code = "campaign.save" />" />
	
	<input type="submit" name="delete"
		value="<spring:message code="campaign.delete" />" 
		onclick="return confirm('<spring:message code = "campaign.confirm.delete"/>')"/>

	<input type="button" name="cancel"
		value="<spring:message code="campaign.cancel" />"
		onclick="javascript: window.location.replace('campaign/sponsor/list.do')" />
			
</form:form>