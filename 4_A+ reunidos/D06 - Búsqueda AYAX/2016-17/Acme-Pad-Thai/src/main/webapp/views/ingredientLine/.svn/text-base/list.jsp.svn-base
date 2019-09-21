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
	
<display:table name = "ingredientLines" id = "row" requestURI = "${requestURI}" pagesize = "10" class = "displaytag" >
		
	
	<spring:message code = "ingredientLine.multiplicity" var = "multiplicityHeader" />
	<display:column property = "multiplicity" title = "${multiplicityHeader}" />
	
	<spring:message code = "ingredientLine.unit" var = "unitHeader" />
	<display:column property = "unit" title = "${unitHeader}" />
	
	<security:authorize access="hasRole('USER')">
		<display:column>	
				<a href="recipe/user/addIngredient.do?ingredientLineId=${row.id}&recipeId=${recipeId}">
					<spring:message code="ingredientLine.add" />
				</a>
		</display:column>
	</security:authorize>	
</display:table>
	
	<input type="button" name="cancel"
	value="<spring:message code="ingredientLine.cancel" />"
	onclick="javascript: window.location.replace('recipe/showRecipe.do?recipeId=${recipeId}')" />
	