<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="finder/explorer/save.do" modelAttribute="finder">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="explorer"/>
	
	<form:label path="keyword"><spring:message code="finder.keyword"/></form:label>
	<form:input path="keyword"/>
	<form:errors path="keyword" cssClass="error"/>
	<br/>
	
	<form:label path="minimumprice"><spring:message code="finder.minimumprice"/></form:label>
	 <form:input path="priceMin" placeholder="999.00$"/>
	<form:errors path="minimumprice" cssClass="error"/>
	<br/>
	
	<form:label path="maximumprice"><spring:message code="finder.maximumprice"/></form:label>
	<form:input path="priceMax" placeholder="999.00$"/>
	<form:errors path="maximumprice" cssClass="error"/>
	<br/>
	
	<form:label path="rangeofstartingdate"><spring:message code="finder.rangeofstatrtingdate"/></form:label>
	<form:input path="startDateTripMin"/>
	<form:errors path="Rangeofstartingdate" cssClass="error"/>
	
	<form:input path="startDateTripMax"/>
	<form:errors path="rangeofstartingdate" cssClass="error"/>
	<br/>
	
	
	
	
	<input type="submit" name="search" value="<spring:message code="finder.save"/>"/>
	
	<input type="button" name="cancel" value="<spring:message code="finder.cancel" />" onclick="javascript: relativeRedir('finder/explorer/list.do');" />
	
</form:form>