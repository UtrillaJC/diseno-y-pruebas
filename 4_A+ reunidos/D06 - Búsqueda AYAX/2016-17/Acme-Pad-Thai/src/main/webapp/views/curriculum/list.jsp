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
	
<display:table name = "curricula" id = "row" requestURI = "curriculum/nutritionist/list.do" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "curriculum.photo" var = "photoHeader" />
	<display:column property = "photo" title = "${photoHeader}" sortable = "false"/>
	
	<spring:message code = "curriculum.educationSection" var = "educationSectionHeader" />
	<display:column property = "educationSection" title = "${educationSectionHeader}" sortable = "false"/>

	<spring:message code = "curriculum.experienceSection" var = "experienceSectionHeader" />
	<display:column property = "experienceSection" title = "${experienceSectionHeader}" sortable = "false"/>
	
	<spring:message code = "curriculum.hobbiesSection" var = "hobbiesSectionHeader" />
	<display:column property = "hobbiesSection" title = "${hobbiesSectionHeader}" sortable = "false"/>
	
	<security:authorize access="hasRole('NUTRITIONIST')">
		<display:column>
			<a href="curriculum/nutritionist/edit.do?curriculumId=${row.id}">
				<spring:message code="curriculum.edit" />
			</a>
		</display:column>
		
		<display:column>
			<a href="curriculum/nutritionist/showCurriculum.do?curriculumId=${row.id}">
				<spring:message code="curriculum.showCurriculum" />
			</a>
		</display:column>

	</security:authorize>	
	
</display:table>

	<security:authorize access="hasRole('NUTRITIONIST')">
			<a href="curriculum/nutritionist/create.do">
				<spring:message	code="curriculum.create" /></a>
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="curriculum.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	