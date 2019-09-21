<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
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

<div id="banner">
	<a href="${sponsorship.information}">
		<img src="${sponsorship.banner}">
	</a>
</div>

<h3><b><spring:message code="trip.ticker"/>:&nbsp;</b><jstl:out value="${trip.ticker}"/></h3>
<br/>

<h3><b><spring:message code="trip.title"/>:&nbsp;</b><jstl:out value="${trip.title}"/></h3>
<br/>

<b><spring:message code="trip.description"/>:&nbsp;</b><jstl:out value="${trip.description}"/>
<br/>

<b><spring:message code="trip.category"/>:&nbsp;</b><a href="trip/list.do?categoryId=${trip.category.id}"><jstl:out value="${trip.category.name}"/></a>
<br/>

<b><spring:message code="trip.tags"/>:&nbsp;</b>
<div id="tags">
	<jstl:forEach var="hasValue" items="${trip.hasValues}">
		<jstl:out value="${hasValue.tag}"/>&nbsp;->&nbsp;<jstl:out value="${hasValue.value}"/>
		<br/>
	</jstl:forEach>
</div>
<br/>

<b><spring:message code="trip.manager"/>:&nbsp;</b><a href="manager/display.do?managerId=${trip.manager.id}"><jstl:out value="${trip.manager.name}"/></a>
<br/>

<b><spring:message code="trip.ranger"/>:&nbsp;</b><a href="ranger/display.do?rangerId=${trip.ranger.id}"><jstl:out value="${trip.ranger.name}"/></a>
<br/>

<b><spring:message code="trip.legalText"/>:&nbsp;</b><a href="legalText/display.do?legalTextId=${trip.legalText.id}"><jstl:out value="${trip.legalText.title}"/></a>
<br/>

<b><spring:message code="trip.auditRecords"/>:&nbsp;</b>
<div id="auditRecords">
	<jstl:forEach var="auditRecord" items="${trip.auditRecords}">
		<a href="auditRecord/display.do?auditRecordId=${auditRecord.id}"><jstl:out value="${auditRecord.title}"/></a>
		<br/>
	</jstl:forEach>
	<security:authorize access="hasRole('AUDITOR')">
		<a href="auditRecord/auditor/create.do?tripId=${trip.id}"><spring:message code="trip.createAuditRecord"/></a>
	</security:authorize>
</div>
<br/>

<b><spring:message code="trip.survivalClasses"/>:&nbsp;</b>
<div id="survivalClasses">
	<jstl:forEach var="survivalClass" items="${trip.survivalClasses}">
		<a href="survivalClass/display.do?survivalClassId=${survivalClass.id}"><jstl:out value="${survivalClass.title}"/></a>
		<br/>
	</jstl:forEach>
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${trip.manager.userAccount.username == pageContext.request.userPrincipal.name}">
			<a href="survivalClass/manager/create.do?tripId=${trip.id}"><spring:message code="trip.createSurvivalClass"/></a>
		</jstl:if>
	</security:authorize>
</div>
<br/>

<jstl:if test="${trip.endDateTrip < date}">
	<b><spring:message code="trip.stories"/>:&nbsp;</b>
	<div id="stories">
		<jstl:forEach var="story" items="${trip.stories}">
			<a href="story/display.do?storyId=${story.id}"><jstl:out value="${story.title}"/></a>
			<br/>
		</jstl:forEach>
		<security:authorize access="hasRole('EXPLORER')">
			<jstl:if test="${enrolled == true && trip.endDateTrip < date}">
				<a href="story/explorer/create.do?tripId=${trip.id}"><spring:message code="trip.createStory"/></a>
			</jstl:if>
		</security:authorize>
	</div>
	<br/>
</jstl:if>

<b><spring:message code="trip.publicationDate"/>:&nbsp;</b><jstl:out value="${trip.publicationDate}"/>
<br/>

<b><spring:message code="trip.startDate"/>:&nbsp;</b><jstl:out value="${trip.startDateTrip}"/>
<br/>

<b><spring:message code="trip.endDate"/>:&nbsp;</b><jstl:out value="${trip.endDateTrip}"/>
<br/>

<b><spring:message code="trip.requirements"/>:&nbsp;</b>
<div id="requirements">
	<jstl:forEach var="requirement" items="${trip.requirements}">
		<jstl:out value="${requirement}"/>
		<br/>
	</jstl:forEach>
</div>
<br/>

<jstl:if test="${trip.cancelledReason == true}">
	<b><spring:message code="trip.cancelledReason"/>:&nbsp;</b><jstl:out value="${trip.cancelledReason}"/>
	<br/>
</jstl:if>

<a href="stage/list.do?tripId=${trip.id}"><spring:message code="trip.listStages"/></a>
<br/>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${trip.manager.userAccount.username == pageContext.request.userPrincipal.name}">
		<a href="application/manager/list.do?tripId=${trip.id}"><spring:message code="trip.listApplications"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('EXPLORER')">
	<a href="application/explorer/create.do?tripId=${trip.id}"><spring:message code="trip.createApplication"/></a>
	<br/>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${trip.manager.userAccount.username == pageContext.request.userPrincipal.name}">
		<a href="note/manager/list.do?tripId=${trip.id}"><spring:message code="trip.listNotes"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('AUDITOR')">
	<a href="note/auditor/create.do?tripId=${trip.id}"><spring:message code="trip.createNote"/></a>
	<br/>
</security:authorize>

<security:authorize access="hasRole('SPONSOR')">
	<a href="sponsorship/sponsor/create.do?tripId=${trip.id}"><spring:message code="trip.createNote"/></a>
	<br/>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${trip.manager.userAccount.username == pageContext.request.userPrincipal.name}">
		<a href="trip/manager/edit.do?tripId=${trip.id}"><spring:message code="trip.editTrip"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<input type="button" name="cancel" value="<spring:message code="trip.cancel" />" onclick="javascript: relativeRedir('trip/list.do');" />
