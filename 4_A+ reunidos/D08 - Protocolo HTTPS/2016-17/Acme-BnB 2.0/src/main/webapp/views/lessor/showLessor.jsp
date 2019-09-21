<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2 class = "lessor.name"><spring:message code = "lessor.name"/>: <jstl:out value="${lessor.name}"></jstl:out></h2>

<h2 class = "lessor.surname"><spring:message code = "lessor.surname"/>: <jstl:out value="${lessor.surname}"></jstl:out></h2>

<a><spring:message code = "lessor.email"/>: <jstl:out value="${lessor.email}"></jstl:out></a>
<br />
<br />
<a><spring:message code = "lessor.phone"/>: <jstl:out value="${lessor.phone}"></jstl:out></a>
<br />
<br />

<h1 class = "lessor.comments"><spring:message code = "lessor.comments"/></h1>

<display:table pagesize="5" class="displaytag" name="commentsLessor" requestURI="${requestURI}" id="comment">
	
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

<h1 class = "lessor.socialIdentities"><spring:message code = "lessor.socialIdentities"/></h1>

<display:table pagesize="5" class="displaytag" name="socialIdentitiesLessor" requestURI="${requestURI}" id="socialIdentity">
	
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
	value="<spring:message code="lessor.back" />"
	onclick="history.go(-1)" />
