<%--
 * edit.jsp
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

<form:form action = "socialIdentity/cook/editSocialIdentity.do" modelAttribute = "cook">

	<form:hidden path = "id" />
	<form:hidden path = "version" />
	
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />	
	<form:hidden path="userAccount.username" />
	<form:hidden path="userAccount.password" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.sentMessages" />
	<form:hidden path="userAccount.receivedMessages" />
	<form:hidden path="userAccount.folders" />	
	
	<form:hidden path="masterClasses"/>


	<form:hidden path="name"/>
	<form:hidden path="surname"/>
	<form:hidden path="email"/>
	<form:hidden path="phoneNumber"/>
	<form:hidden path="address"/>


	<fieldset>
		
		<legend>
			<spring:message code="cook.socialIdentity" />
		</legend>
		
			<form:hidden path="socialIdentity.id"/>
			<form:hidden path="socialIdentity.version"/>
			
			<form:label path = "socialIdentity.link">
				<spring:message code = "actor.socialIdentity.link" />:
			</form:label>
			<form:input path = "socialIdentity.link"/>
			<form:errors cssClass = "error" path = "socialIdentity.link"/>
			
			<br />
			<br />
			
			<form:label path = "socialIdentity.name">
				<spring:message code = "actor.socialIdentity.name" />:
			</form:label>
			<form:input path = "socialIdentity.name"/>
			<form:errors cssClass = "error" path = "socialIdentity.name"/>
			
			<br />
			<br />
			
			<form:label path = "socialIdentity.nick">
				<spring:message code = "actor.socialIdentity.nick" />:
			</form:label>
			<form:input path = "socialIdentity.nick"/>
			<form:errors cssClass = "error" path = "socialIdentity.nick"/>
			
			<br />
			<br />
			
			<form:label path = "socialIdentity.picture">
				<spring:message code = "actor.socialIdentity.picture" />:
			</form:label>
			<form:input path = "socialIdentity.picture"/>
			<form:errors cssClass = "error" path = "socialIdentity.picture"/>
		</fieldset>

	<br />
	<br />
			
	<security:authorize access="isAuthenticated()">
		<input type = "submit" name = "save" 
			value = " <spring:message code = "actor.save" />" />
	
		<input type="button" name="cancel"
			value="<spring:message code="actor.cancel" />"
			onclick="javascript: window.location.replace('cook/editProfile.do')" />
	</security:authorize>
				
</form:form>