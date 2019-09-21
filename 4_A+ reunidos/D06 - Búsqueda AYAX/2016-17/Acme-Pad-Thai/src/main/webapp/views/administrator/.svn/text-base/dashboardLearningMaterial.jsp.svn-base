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


<!-- The average number of learning materials per master class, grouped by kind of learning material. -->

<div>
	<h3><spring:message code="administrator.dashboardLearningMaterial.req35" /></h3>
	<div>
		<p>
			<jstl:out value="${avgNumberLearningMaterialsGroupedLearningMaterials}"/>
		</p>
	</div>
</div>

<!-- The number of master classes that have been promoted. -->

<div>
	<h3><spring:message code="administrator.dashboardLearningMaterial.req36" /></h3>
	<div>
		<p>
			<jstl:out value="${numberOfMasterClassPromoted}"/>
		</p>
	</div>
</div>

<!-- The listing of cooks, sorted according to the number of master classes that have been promoted. -->

<h3><spring:message code="administrator.dashboardLearningMaterial.req37" /></h3>

<display:table name="listOfCookOrderByNumMAsterClassPromoted" id="cook" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code = "administrator.cook.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>
	
	<spring:message code = "administrator.cook.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}" sortable = "false"/>

	<spring:message code = "administrator.cook.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}" sortable = "false"/>
	
</display:table>

<!-- The average number of promoted master classes per cook. -->

<div>
	<h3><spring:message code="administrator.dashboardLearningMaterial.req38" /></h3>
	<div>
		<p>
			<jstl:out value="${avgNumberPromotedPerCook}"/>
		</p>
	</div>
</div>

<!-- The average number of demoted master classes per cook. -->

<div>
	<h3><spring:message code="administrator.dashboardLearningMaterial.req39" /></h3>
	<div>
		<p>
			<jstl:out value="${avgNumberDemotedPerCook}"/>
		</p>
	</div>
</div>

<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>


