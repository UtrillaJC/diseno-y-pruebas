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
	
<display:table name = "ingredients" id = "row" requestURI = "ingredient/nutritionist/list.do" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "ingredient.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" sortable = "false"/>
	
	<spring:message code = "ingredient.description" var = "descriptionHeader" />
	<display:column property = "description" title = "${descriptionHeader}" sortable = "false"/>

	<spring:message code = "ingredient.picture" var = "pictureHeader" />
	<display:column property = "picture" title = "${pictureHeader}" sortable = "false"/>
	
	<security:authorize access="hasRole('NUTRITIONIST')">
		<display:column>
			<a href="ingredient/nutritionist/edit.do?ingredientId=${row.id}">
				<spring:message code="ingredient.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	<display:column>
		<a href="ingredient/nutritionist/showIngredient.do?ingredientId=${row.id}">
			<spring:message code="ingredient.showIngredient" />
		</a>
	</display:column>		
	<security:authorize access="hasRole('USER')">
		<display:column>
			<a href="ingredient/user/add.do?ingredientId=${row.id}&recipeId=${recipeId}&ingredientLineId=${ingredientLineId}">
				<spring:message code="ingredient.add" />
			</a>
		</display:column>
	</security:authorize>	
	
</display:table>

	<security:authorize access="hasRole('NUTRITIONIST')">
			<a href="ingredient/nutritionist/create.do">
				<spring:message	code="ingredient.create" /></a>
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="ingredient.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />	