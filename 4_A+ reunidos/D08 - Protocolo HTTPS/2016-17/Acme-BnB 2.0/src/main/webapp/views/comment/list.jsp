<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" name="comments" requestURI="${requestURI}" id="comment">
	
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
	<display:column property="author" title="${author}"
		sortable="false" />
		
	
		
	<!-- Action links -->

</display:table>


	
	<input type="button" value="<spring:message code="comment.return.link" />"
	onclick="javascript: history.back()" />
	
