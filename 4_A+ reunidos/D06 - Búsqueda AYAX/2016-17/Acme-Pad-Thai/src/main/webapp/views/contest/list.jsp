<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	
<jstl:if test="${execute == true}">	
	<h2><spring:message code="contest.finished"/></h2>
</jstl:if>

	
<!-- Listing grid -->
<display:table  name="contests" id="row" requestURI="contest/list.do"  pagesize="5" class="displaytag">
	
	<!-- Attributes -->
	
	<spring:message code="contest.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="contest.momentOpening" var="momentOpeningHeader" />
	<display:column property="momentOpening" title="${momentOpeningHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
	
	<spring:message code="contest.momentClosing" var="momentClosingHeader" />
	<display:column property="momentClosing" title="${momentClosingHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
	
	<display:column>
		<a href="recipe/listWinnersContest.do?contestId=${row.id}">
			<spring:message code="contest.recipesWinners" />
		</a>
	</display:column>
	
	<display:column>
		<a href="recipe/listQualifyByContest.do?contestId=${row.id}">
			<spring:message code="contest.recipesQualifies" />
		</a>
	</display:column>
<jstl:if test="${execute == true}">	
	<display:column>
		<a href="contest/administrator/execute.do?contestId=${row.id}">
			<spring:message code="contest.execute" />
		</a>
	</display:column>
</jstl:if>
	<security:authorize access = "hasRole('USER')">
		<display:column>
			<a href="registration/user/register.do?contestId=${row.id}">
				<spring:message code="contest.registerRecipe" />
			</a>
		</display:column>
	</security:authorize>
	
	<security:authorize access = "hasRole('ADMINISTRATOR')">
		<display:column>
			<a href="contest/administrator/edit.do?contestId=${row.id}">
				<spring:message code="contest.edit" />
			</a>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access = "hasRole('ADMINISTRATOR')">
		<a href="contest/administrator/create.do">
			<spring:message code="contest.create" />
		</a>
</security:authorize>
<br />
<br />
<input type="button" name="cancel"
	value="<spring:message code="contest.back" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
		

