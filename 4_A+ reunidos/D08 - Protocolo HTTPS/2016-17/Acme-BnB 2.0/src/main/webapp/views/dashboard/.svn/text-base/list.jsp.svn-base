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
		<tr><td style="width:75%"><b><spring:message code="dashboard.accepted"/></b></td>
			<td><jstl:out value= "${dashb1}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.request"/></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.denied"/></b></td>
			<td><jstl:out value= "${dashb2}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.request"/></td>
		</tr>
	</table>
<br>
	<h3><spring:message code="dashboard.dashboard2"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.accepted"/></b></td>
			<td><jstl:out value= "${dashb3}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.request"/></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.denied"/></b></td>
			<td><jstl:out value= "${dashb4}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.request"/></td>
		</tr>
	</table>
<br>
	
	<h3><spring:message code="dashboard.dashboard3"/></h3>
	<display:table name="dashb5" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
			   
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />

	<h3><spring:message code="dashboard.dashboard4"/></h3>
	<display:table name="dashb6" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="userAccount"/>
		<display:column property="userAccount.username" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />		
	<h3><spring:message code="dashboard.dashboard5"/></h3>
	<display:table name="dashb7" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />		

	<h3><spring:message code="dashboard.dashboard6"/></h3>
	<display:table name="dashb8" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />		

	<h3><spring:message code="dashboard.dashboard7"/></h3>
	<display:table name="dashb9" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />	

	<h3><spring:message code="dashboard.dashboard8"/></h3>
	<display:table name="dashb10" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />	

	<h3><spring:message code="dashboard.dashboard9"/></h3>
	<display:table name="dashb11" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
	
	<h3><spring:message code="dashboard.dashboard10"/></h3>
	<display:table name="dashb12" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />	
	<h3><spring:message code="dashboard.dashboard11"/></h3>
	<display:table name="dashb13" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
	
	<h3><spring:message code="dashboard.dashboard12"/></h3>
	<display:table name="dashb14" id="row" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">
	
		<spring:message code="dashboard.user" var="name"/>
		<display:column property="name" title="${userAccount}" sortable="false"/>
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>
	
		<spring:message code="dashboard.surname"  var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false"/>
	
	</display:table>
<br />	
	<h3><spring:message code="dashboard.dashboard13"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value= "${dashb15}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.result"/></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.max"/></b></td>
			<td><jstl:out value= "${dashb16}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.result"/></td>
		</tr>
		<tr><td style="width:75%"><b><spring:message code="dashboard.min"/></b></td>
			<td><jstl:out value= "${dashb17}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.result"/></td>
		</tr>
	</table>
<br>

<h3><spring:message code="dashboard.dashboard14"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:75%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value= "${dashb18}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.audits"/></td>
		</tr>	
		<tr><td style="width:75%"><b><spring:message code="dashboard.max"/></b></td>
			<td><jstl:out value= "${dashb19}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.audits"/></td>
		</tr>
		<tr><td style="width:75%"><b><spring:message code="dashboard.min"/></b></td>
			<td><jstl:out value= "${dashb20}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.audits"/></td>
		</tr>
	</table>
<br>

	<h3><spring:message code="dashboard.dashboard15"/></h3>
	<display:table name="dashb21" id="rowAttribute" requestURI="administrator/dashboard.do" 
			   pagesize="5" class="displaytag">			   
		
		<spring:message code="dashboard.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false"/>

	<spring:message code="dashboard.use" var="valueAttributes"/>
		<display:column value="${rowAttribute.valueAttributes.size()}" title="${valueAttributes}"/>


	</display:table>
	
<br />

<h3><spring:message code="dashboard.dashboard16"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:33%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value= "${dashb22}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.socialIdentities"/></td>
		</tr>	
		<tr><td style="width:33%"><b><spring:message code="dashboard.max"/></b></td>
			<td><jstl:out value= "${dashb23}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.socialIdentities"/></td>
		</tr>
		<tr><td style="width:33%"><b><spring:message code="dashboard.min"/></b></td>
			<td><jstl:out value= "${dashb24}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.socialIdentities"/></td>
		</tr>
	</table>
<br>

<h3><spring:message code="dashboard.dashboard17"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:33%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value= "${dashb25}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.invoices"/></td>
		</tr>	
		<tr><td style="width:33%"><b><spring:message code="dashboard.max"/></b></td>
			<td><jstl:out value= "${dashb26}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.invoices"/></td>
		</tr>
		<tr><td style="width:33%"><b><spring:message code="dashboard.min"/></b></td>
			<td><jstl:out value= "${dashb27}"> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.invoices"/></td>
		</tr>
	</table>
<br>

<h3><spring:message code="dashboard.dashboard18"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:33%"><b><spring:message code="dashboard.total"/></b></td>
			<td><jstl:out value= "${dashb28}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.euros"/></td>
		</tr>	

	</table>
<br>

<h3><spring:message code="dashboard.dashboard19"/></h3>
	<table style="width:20%"> 
		<tr><td style="width:33%"><b><spring:message code="dashboard.average"/></b></td>
			<td><jstl:out value= "${dashb29}"><br><br> </jstl:out>&nbsp;&nbsp;<spring:message code="dashboard.request"/></td>
		</tr>	

	</table>
<br>

<input type="button" value="<spring:message code="dashboard.return" />" onclick="javascript: history.back()" />	

</security:authorize>