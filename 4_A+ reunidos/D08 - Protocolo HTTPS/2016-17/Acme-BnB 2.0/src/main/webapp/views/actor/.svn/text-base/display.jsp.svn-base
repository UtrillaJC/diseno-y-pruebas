<%--
 * register.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<div>
<form:form action="actor/display.do" modelAttribute="actorDisplay">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount"/>
	<form:hidden path="socialIdentities"/>
	<form:hidden path="commentCreates"/>
	<security:authorize access="hasRole('LESSOR')">
		<form:hidden path="properties" />
		<form:hidden path="amount" />
	</security:authorize>
	<security:authorize access="hasRole('AUDITOR')">
		<form:hidden path="audits" />
	</security:authorize>
	<security:authorize access="hasRole('TENANT')">
		<form:hidden path="requests" />
	</security:authorize>

	<acme:textbox code="actor.name" path="name" readonly="true"/>
	
	<acme:textbox code="actor.surname" path="surname" readonly="true"/>
	
	<acme:textbox code="actor.email" path="email" readonly="true"/>
	
	<acme:textbox code="actor.phone" path="phone" readonly="true"/>
	
	<acme:textbox code="actor.picture" path="picture" readonly="true"/>
	</form:form>
	</div>
	<br />
	<security:authorize access="hasAnyRole('LESSOR')">		
		<jstl:if test="${expieredCC}">
		<jstl:out value="${vez}"/>
		<form:form  id="form1" action="lessor/updateCreditCard.do" modelAttribute="creditCard">
		<div style="background-color:#f44336; color: rgba(255,255,255, 0.84);display: inline;">
			<spring:message code="creditCardExpired"/>
			<a style="color: rgba(255,255,255, 0.84);cursor:pointer; cursor: hand" onclick="document.getElementById('form1').submit();"><spring:message code="creditCardClickUpdate"/></a>
		</div>
		<fieldset>
		
	<acme:textbox code="creditCard.brandName" path="brandName"/>
	
	<acme:textbox code="creditCard.holderName" path="holderName"/>
	
	<acme:textbox code="creditCard.number" path="number"/>
	
	<acme:textbox code="creditCard.expirationMonth" path="expirationMonth"/>
	
	<acme:textbox code="creditCard.expirationYear" path="expirationYear"/>
	
	<acme:textbox code="creditCard.cvvCode" path="cvvCode"/>
	<br />
	</fieldset>
	</form:form>
		</jstl:if>
		
	</security:authorize>
	<security:authorize access="hasAnyRole('LESSOR','TENANT')">				
	<h1 class = "lessorOrTenant.comments"><spring:message code = "lessorOrTenant.comments"/></h1>

<display:table pagesize="5" class="displaytag" name="commentsLessorOrTenant" requestURI="${requestURI}" id="comment">
	
	<!-- Attributes -->
	
		
		<spring:message code="comment.title" var="title" />
	<display:column property="title" title="${title}"
		sortable="false" />
		
		<spring:message code="comment.moment" var="moment" />
	<display:column property="moment" title="${moment}"
		sortable="false" />


	<spring:message code="comment.text" var="text" />
	<display:column property="text" title="${text}"
		sortable="false" />
	
	<spring:message code="comment.stars" var="stars" />
	<display:column property="stars" title="${stars}"
		sortable="false" />
		
		
		<spring:message code="comment.author" var="author" />
	<display:column property="author.name" title="${author}"
		sortable="false" />
		
	
		
	<!-- Action links -->

</display:table>	

</security:authorize>

<h1 class = "actor.socialIdentities"><spring:message code = "actor.socialIdentities"/></h1>

<display:table pagesize="5" class="displaytag" name="socialIdentitiesActor" requestURI="${requestURI}" id="socialIdentity">
	
	<!-- Attributes -->
	
		
		<spring:message code="socialIdentity.nick" var="nick" />
	<display:column property="nick" title="${nick}"
		sortable="false" />
		
		<spring:message code="socialIdentity.socialNetwork" var="socialNetwork" />
	<display:column property="socialNetwork" title="${socialNetwork}"
		sortable="false" />


	<spring:message code="socialIdentity.profile" var="profile" />
	<display:column property="profile" title="${profile}"
		sortable="false" />
	
	<display:column >
		
	<button type="button" onclick="location.href='socialidentity/edit.do?si_id=${socialIdentity.id}'">
		<spring:message code="edit" />
		</button>
		
		</display:column>
	<!-- Action links -->

</display:table>	
	<br/>
	<acme:button href="socialidentity/create.do" code="actor.addSocialId"/>

	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />" 
		onclick="javascript: location.replace('welcome/index.do')" />
