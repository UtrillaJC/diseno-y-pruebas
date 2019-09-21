<%--
 * list.jsp
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


<display:table name = "persons" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

	<spring:message code = "person.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "true"/>

	<spring:message code = "person.surname" var = "surnameHeader" />
	<display:column property = "surname" title = "${surnameHeader}"/>
	
	<spring:message code = "person.email" var = "emailHeader" />
	<display:column property = "email" title = "${emailHeader}"/>

<security:authorize access="hasRole('USER')">

	<jstl:choose>
		<jstl:when test = "${following == true}">
			<display:column>
				<a href="person/unfollow.do?personId=${row.id}"
					onclick="return confirm('<spring:message code = "person.confirm.unfollow"/>')">
					<spring:message code="person.unfollow"/>
				
				</a>
			</display:column>		
		</jstl:when>
		<jstl:when test = "${following == false}">
			<display:column>	
				<a href="person/follow.do?personId=${row.id}">
					<spring:message code="person.follow"/>
				</a>
			</display:column>
		</jstl:when>
	</jstl:choose>		
</security:authorize>	

<security:authorize access="hasRole('NUTRITIONIST')">

	<jstl:choose>
		<jstl:when test = "${following == true}">
			<display:column>
				<a href="person/unfollowNutritionist.do?personId=${row.id}"
					onclick="return confirm('<spring:message code = "person.confirm.unfollow"/>')">
					<spring:message code="person.unfollow"/>
				
				</a>
			</display:column>		
		</jstl:when>
		<jstl:when test = "${following == false}">
			<display:column>	
				<a href="person/followNutritionist.do?personId=${row.id}">
					<spring:message code="person.follow"/>
				</a>
			</display:column>
		</jstl:when>
	</jstl:choose>		
</security:authorize>	

</display:table>

<br/>
	
<input type="button" name="cancel"
value="<spring:message code="person.back" />"
onclick="javascript: window.location.replace('welcome/index.do')" />