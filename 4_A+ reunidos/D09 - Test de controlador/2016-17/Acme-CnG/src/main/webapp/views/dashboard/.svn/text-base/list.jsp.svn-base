<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
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


<security:authorize access="hasRole('ADMINISTRATOR')">
	<h3><spring:message code="dashboard.dashboard1"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.offers"/></b></td>
			<td><jstl:out value="${dashb1}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.requests"/></b></td>
			<td><jstl:out value="${dashb2}"> </jstl:out></td>
		</tr>
	</table>
<br>
	<h3><spring:message code="dashboard.dashboard2"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value="${dashb3}"></jstl:out></td>
		</tr>	
	</table>
<br>
	<h3><spring:message code="dashboard.dashboard3"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.offers"/></b></td>
			<td><jstl:out value="${dashb4}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.requests"/></b></td>
			<td><jstl:out value="${dashb5}"> </jstl:out></td>
		</tr>
	</table>
<br>
	<h3><spring:message code="dashboard.dashboard4"/></h3>
	<display:table name="dashb6" id="rowAccepted" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
	</display:table>
<br />
	<h3><spring:message code="dashboard.dashboard5"/></h3>
	<display:table name="dashb7" id="rowDenied" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
	</display:table>
<br />
	<h3><spring:message code="dashboard.dashboard6"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.actors"/></b></td>
			<td><jstl:out value="${dashb8}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.offers"/></b></td>
			<td><jstl:out value="${dashb9}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.requests"/></b></td>
			<td><jstl:out value="${dashb10}"> </jstl:out></td>
		</tr>
	</table>
<br>
	<h3><spring:message code="dashboard.dashboard7"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.actors"/></b></td>
			<td><jstl:out value="${dashb11}"></jstl:out></td>
		</tr>	
	</table>
<br>

	<h3><spring:message code="dashboard.dashboard8"/></h3>
	<display:table name="dashb12" id="rowActor" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
	</display:table>
<br />
	<h3><spring:message code="dashboard.dashboard9"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.min"/></b></td>
			<td><jstl:out value="${dashb13}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.max"/></b></td>
			<td><jstl:out value="${dashb14}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value="${dashb15}"> </jstl:out></td>
		</tr>
	</table>
<br>
	<h3><spring:message code="dashboard.dashboard10"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.min"/></b></td>
			<td><jstl:out value="${dashb16}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.max"/></b></td>
			<td><jstl:out value="${dashb17}"></jstl:out></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value="${dashb18}"> </jstl:out></td>
		</tr>
	</table>
<br>

	<h3><spring:message code="dashboard.dashboard11"/></h3>
	<display:table name="dashb6" id="rowAccepted" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
	</display:table>
<br />

	<h3><spring:message code="dashboard.dashboard12"/></h3>
	<display:table name="dashb6" id="rowAccepted" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
	</display:table>
<br />
<input type="button" value="<spring:message code="dashboard.return" />" onclick="javascript: history.back()" />	

</security:authorize>