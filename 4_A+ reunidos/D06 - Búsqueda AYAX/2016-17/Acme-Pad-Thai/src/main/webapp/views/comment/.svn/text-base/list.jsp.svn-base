
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Comment's list-->

<display:table  name="comments" id="row" requestURI="comment/list.do" pagesize="5" class="displaytag">
		
	<spring:message code="comment.title" var="titleHeader" /> 
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="comment.text" var="textHeader" /> 
	<display:column property="text" title="${textHeader}" />
	
	<spring:message code="comment.stars" var="starsHeader" /> 
	<display:column property="stars" title="${starsHeader}" />
	
</display:table>

	<br />
	<input type="button" name="cancel"
		value="<spring:message code="comment.back" />"
		onclick="javascript: window.location.replace('item/list.do')" />
		