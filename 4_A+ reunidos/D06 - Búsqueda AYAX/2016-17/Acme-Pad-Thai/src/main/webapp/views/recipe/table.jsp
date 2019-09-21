<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('NUTRITIONIST') || hasRole('USER')">
<jstl:choose>
	<jstl:when test="${intTaste == 0}">
		<h2><spring:message code="recipe.not.tastes" /></h2>
	</jstl:when>

	<jstl:when test="${intTaste == 1}">
		<h2><spring:message code="recipe.ilikes" /></h2>
	</jstl:when>
	
	<jstl:when test="${intTaste == 2}">
		<h2><spring:message code="recipe.idontlikes" /></h2>
	</jstl:when>
</jstl:choose>
</security:authorize>	

<display:table name = "recipes" id = "row" requestURI = "${requestURI }" pagesize = "5" class = "displaytag" >

	<spring:message code = "recipe.title" var = "titleHeader" />
	<display:column property = "title" title = "${titleHeader}" sortable = "true"/>

	<spring:message code = "recipe.momentAuthored" var = "momentAuthoredHeader" />
	<display:column property = "momentAuthored" title = "${momentAuthoredHeader}"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable = "false"/>
	
	<spring:message code = "recipe.momentUpdated" var = "momentUpdatedHeader" />
	<display:column property = "momentUpdated" title = "${momentUpdatedHeader}" 
		format="{0,date,dd/MM/yyyy HH:mm}" sortable = "false"/>
	
	<spring:message code = "recipe.likes" var = "likesHeader" />
	<display:column property = "likes" title = "${likesHeader}" sortable = "false"/>
	
	<spring:message code = "recipe.dislikes" var = "dislikesHeader" />
	<display:column property = "dislikes" title = "${dislikesHeader}" sortable = "false"/>

	<display:column>
		<a href="user/showUser.do?userId=${row.user.id}">
			<spring:message code="recipe.authored" />
		</a>
	</display:column>	
	

	
	<jstl:choose>
		<jstl:when test="${owner}">
		<security:authorize access="hasRole('USER')">	
			<display:column>
					<a href="recipe/user/edit.do?recipeId=${row.id}">
						<spring:message code="recipe.edit" />
					</a>		
			</display:column>	
			
			<display:column>
				<a href="recipe/user/showRecipe.do?recipeId=${row.id}">
					<spring:message code="recipe.showRecipe" />
				</a>
			</display:column>
		
		</security:authorize>	
		</jstl:when>
		<jstl:when test="${!owner}">
			<display:column>
				<a href="recipe/showRecipe.do?recipeId=${row.id}">
					<spring:message code="recipe.showRecipe" />
				</a>
			</display:column>
		</jstl:when>
	</jstl:choose>
	
<security:authorize access="hasRole('NUTRITIONIST') || hasRole('USER')">
	<jstl:choose>
		<jstl:when test="${intTaste == 0}">
				<display:column>
						<a href="recipe/likes.do?recipeId=${row.id}">
							<spring:message code="recipe.likes" />
						</a>
				</display:column>
				<display:column>
						<a href="recipe/dislikes.do?recipeId=${row.id}">
							<spring:message code="recipe.dislikes" />
						</a>
				</display:column>	
		</jstl:when>
	</jstl:choose>
</security:authorize>	
</display:table>

<security:authorize access = "hasRole('USER')">
<input type="button" name="create"
  value="<spring:message code="recipe.create" />"
  onclick="javascript: window.location.replace('recipe/user/create.do')" />
</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="recipe.back" />"
	onclick="history.go(-1)" />