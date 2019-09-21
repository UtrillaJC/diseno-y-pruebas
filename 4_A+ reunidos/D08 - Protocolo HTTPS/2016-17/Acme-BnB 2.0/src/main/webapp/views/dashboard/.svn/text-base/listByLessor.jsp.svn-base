<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<security:authorize access="hasRole('ADMINISTRATOR')">

	<h3><spring:message code="dashboard.lessor1"/></h3>
	<display:table name="dashb1" id="rowProperty" requestURI="administrator/listByLessor.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
		
</display:table>
<br />
	<h3><spring:message code="dashboard.lessor2"/></h3>
	<display:table name="dashb2" id="rowProperty" requestURI="administrator/listByLessor.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
		
</display:table>
<br />

	<h3><spring:message code="dashboard.lessor3"/></h3>
	<display:table name="dashb3" id="rowProperty" requestURI="administrator/listByLessor.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
		
</display:table>
<br />

	<h3><spring:message code="dashboard.lessor4"/></h3>
	<display:table name="dashb4" id="rowProperty" requestURI="administrator/listByLessor.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
		
</display:table>
<br />

	<h3><spring:message code="dashboard.lessor5"/></h3>
	<display:table name="dashb5" id="rowProperty" requestURI="administrator/listByLessor.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
		
</display:table>
<br />

<input type="button" value="<spring:message code="dashboard.return" />" onclick="javascript: history.back()" />	

</security:authorize>


