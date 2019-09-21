<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2 class = "tenant.name"><spring:message code = "tenant.name"/>: <jstl:out value="${tenant.name}"></jstl:out></h2>

<h2 class = "tenant.surname"><spring:message code = "tenant.surname"/>: <jstl:out value="${tenant.surname}"></jstl:out></h2>

<a><spring:message code = "tenant.email"/>: <jstl:out value="${tenant.email}"></jstl:out></a>
<br />
<br />
<a><spring:message code = "tenant.phone"/>: <jstl:out value="${tenant.phone}"></jstl:out></a>
<br />
<br />

<h1 class = "tenant.comments"><spring:message code = "tenant.comments"/></h1>

<display:table pagesize="5" class="displaytag" name="commentsTenant" requestURI="${requestURI}" id="comment">
	
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

<h1 class = "tenant.socialIdentities"><spring:message code = "tenant.socialIdentities"/></h1>

<display:table pagesize="5" class="displaytag" name="socialIdentitiesTenant" requestURI="${requestURI}" id="socialIdentity">
	
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
		
	
		
	<!-- Action links -->

</display:table>	

	

<input type="button" name="cancel"
	value="<spring:message code="tenant.back" />"
	onclick="history.go(-1)" />
