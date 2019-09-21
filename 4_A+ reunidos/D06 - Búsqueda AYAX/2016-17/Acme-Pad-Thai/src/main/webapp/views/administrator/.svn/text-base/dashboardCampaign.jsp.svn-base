<%--
 * dashboard.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!-- The minimum number of campaigns per sponsor. -->
<div>
	<h3><spring:message code="administrator.dashboardCampaign.req16" /></h3>
	<div>
		<p>
			<jstl:out value="${findMinNumber}"/>
		</p>
	</div>
</div>

<!-- The average number of campaigns per sponsor. -->

<div>
	<h3><spring:message code="administrator.dashboardCampaign.req17" /></h3>
	<div>
		<p>
			<jstl:out value="${findAvgNumber}"/>
		</p>
	</div>
</div>

<!-- The maximum number of campaigns per sponsor. -->

<div>
	<h3><spring:message code="administrator.dashboardCampaign.req18" /></h3>
	<div>
		<p>
			<jstl:out value="${findMaxNumber}"/>
		</p>
	</div>
</div>

<!-- The minimum number of active campaigns per sponsor. -->

<div>
	<h3><spring:message code="administrator.dashboardCampaign.req19" /></h3>
	<div>
		<p>
			<jstl:out value="${findMinNumberActiveCampaign}"/>
		</p>
	</div>
</div>

<!-- The average number of active campaigns per sponsor. -->

<div>
	<h3><spring:message code="administrator.dashboardCampaign.req20" /></h3>
	<div>
		<p>
			<jstl:out value="${findAvgNumberActiveCampaign}"/>
		</p>
	</div>
</div>

<!-- The maximum number of active campaigns per sponsor. -->

<div>
	<h3><spring:message code="administrator.dashboardCampaign.req21" /></h3>
	<div>
		<p>
			<jstl:out value="${findMaxNumberActiveCampaign}"/>
		</p>
	</div>
</div>



<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


