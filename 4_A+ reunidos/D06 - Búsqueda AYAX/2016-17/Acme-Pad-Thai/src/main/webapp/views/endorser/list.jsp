<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

		
	<!-- Listing table -->
	
<display:table name = "endorsers" id = "row" requestURI = "endorser/nutritionist/list.do" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "endorser.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "false"/>
	
	<spring:message code = "endorser.homePage" var = "homePageHeader" />
	<display:column property = "homePage" title = "${homePageHeader}" sortable = "false"/>
	
	<display:column>	
		<a href="endorser/nutritionist/add.do?endorserId=${row.id}&curriculumId=${curriculumId}">
			<spring:message code="endorser.add" />
		</a>
	</display:column>
	
</display:table>

	<security:authorize access="hasRole('NUTRITIONIST')">
			<a href="endorser/nutritionist/create.do">
				<spring:message	code="endorser.create" /></a>
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="endorser.cancel" />"
	onclick="javascript: window.location.replace('curriculum/nutritionist/list.do')" />
	