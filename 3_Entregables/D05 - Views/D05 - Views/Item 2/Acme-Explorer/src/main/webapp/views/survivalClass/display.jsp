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

<h3><b><spring:message code="survivalClass.title"/>:&nbsp;</b><jstl:out value="${survivalClass.title}"/></h3>
<br/>

<b><spring:message code="survivalClass.description"/>:&nbsp;</b><jstl:out value="${survivalClass.description}"/>
<br/>

<b><spring:message code="survivalClass.trip"/>:&nbsp;</b><a href="trip/display.do?tripId=${survivalClass.trip.id}"><jstl:out value="${survivalClass.trip.ticker}"/></a>
<br/>

<b><spring:message code="survivalClass.manager"/>:&nbsp;</b><a href="manager/display.do?managerId=${survivalClass.manager.id}"><jstl:out value="${survivalClass.manager.name}"/></a>
<br/>

<fieldset>
	<legend><spring:message code="survivalClass.GPSCoordinates"/></legend>
	
	<b><spring:message code="survivalClass.GPSCoordinates.name"/>:&nbsp;</b><jstl:out value="${survivalClass.location.name}"/>
	<br/>
	
	<b><spring:message code="survivalClass.GPSCoordinates.longitude"/>:&nbsp;</b><jstl:out value="${survivalClass.location.longitude}"/>
	<br/>
	
	<b><spring:message code="survivalClass.GPSCoordinates.latitude"/>:&nbsp;</b><jstl:out value="${survivalClass.location.latitude}"/>
	<br/>
	
</fieldset>

<security:authorize access="hasRole('EXPLORER')">
	<jstl:choose>
		<jstl:when test="${enrolled == false && survivalClass.trip.endDateTrip > date}">
			<a href="survivalClass/explorer/enrol.do?survivalClassId=${survivalClass.id}"><spring:message code="survivalClass.enrol"/></a>
		</jstl:when>
		<jstl:when test="${enrolled == true && survivalClass.trip.endDateTrip > date}">
			<a href="survivalClass/explorer/disenrol.do?survivalClassId=${survivalClass.id}"><spring:message code="survivalClass.disenrol"/></a>
		</jstl:when>
		<jstl:otherwise>
		</jstl:otherwise>
	</jstl:choose>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${survivalClass.manager.userAccount.username == pageContext.request.userPrincipal.name && survivalClass.trip.startDateTrip > date}">
		<a href="survivalClass/manager/edit.do?survivalClassId=${survivalClass.id}"><spring:message code="survivalClass.editSurvivalClass"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<input type="button" name="cancel" value="<spring:message code="survivalClass.cancel" />" onclick="javascript: relativeRedir('trip/display.do?tripId=${survivalClass.trip.id}');" />
