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
<div id = ingredientOut>
<h2 class = "ingredient.name"><spring:message code = "ingredient.name"/>: ${ingredient.name}</h2>

<h4><spring:message code = "ingredient.description"/>: ${ingredient.description}</h4>

<h4><spring:message code = "ingredient.picture"/>: ${ingredient.picture}</h4>
</div>
<br />

<div id = PropertyOut>
<h2><spring:message code = "ingredient.properties"/></h2>
<jstl:forEach items="${properties}"  var = "p">
	<h4><jstl:out value="${p.name}"/></h4>
</jstl:forEach>
</div>

<br/>

	<security:authorize access="hasRole('NUTRITIONIST')">
		<input type="button" name="AddProperty"
			value="<spring:message code="ingredient.add.property" />"
			onclick="javascript: window.location.replace('ingredient/nutritionist/addProperty.do?ingredientId=${ingredient.id}')" />
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="ingredient.cancel" />"
	onclick="javascript: window.location.replace('ingredient/nutritionist/list.do')" />

</div>