<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id = header>
<div id = curriculumOut>

<h4><spring:message code = "curriculum.photo"/>: ${curriculum.photo}</h4>

<h4><spring:message code = "curriculum.educationSection"/>: ${curriculum.educationSection}</h4>

<h4><spring:message code = "curriculum.experienceSection"/>: ${curriculum.experienceSection}</h4>

<h4><spring:message code = "curriculum.hobbiesSection"/>: ${curriculum.hobbiesSection}</h4>

</div>
<br />

<div id = EndorserOut>
<h2><spring:message code = "curriculum.endorsers"/></h2>
<jstl:forEach items="${endorsers}"  var = "e">
	<h4><jstl:out value="${e.name}"/></h4>
	<h4><jstl:out value="${e.homePage}"/></h4>
	
</jstl:forEach>
</div>

<br/>

	<security:authorize access="hasRole('NUTRITIONIST')">
		<input type="button" name="AddEndorser"
			value="<spring:message code="curriculum.add.endorser" />"
			onclick="javascript: window.location.replace('curriculum/nutritionist/addEndorser.do?curriculumId=${curriculum.id}')" />
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="curriculum.cancel" />"
	onclick="javascript: window.location.replace('curriculum/nutritionist/list.do')" />

</div>