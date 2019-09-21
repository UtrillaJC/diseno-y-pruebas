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
		
		<spring:message code="comment.createdMoment" var="createdMoment" />
	<display:column property="createdMoment" title="${createdMoment}"
		sortable="false" format="{0,date,dd/MM/yyyy}"/>


	<spring:message code="comment.text" var="text" />
	<display:column property="text" title="${text}"
		sortable="false" />
	
	<spring:message code="comment.stars" var="stars" />
	<display:column property="stars" title="${stars}"
		sortable="false" />
		
				
			<spring:message code="comment.author" var="author" />
			<display:column property="author.name" title="${author}" sortable="false"/>

		<display:column>
			<jstl:if test="${comment.banned==true}">
				<font color=#FF0000><spring:message	code="comment.banned"/></font>
			</jstl:if>
		</display:column>
		
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
		
			<jstl:if test="${comment.banned==false}">
				<a  href="comment/administrator/banComment.do?commentId=${comment.id}"><spring:message
							code="comment.ban" /></a>
						
			</jstl:if>
		</display:column>
	
	</security:authorize>
			
		
	
		
	<!-- Action links -->

</display:table>


	
	<input type="button" value="<spring:message code="comment.return.link" />"
	onclick="javascript: history.back()" />
	
