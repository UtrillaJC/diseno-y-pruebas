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


<h2 class="banner"><spring:message code="recipe.banners"/></h2>

<spring:message code="banner.description" />
:
<jstl:out value="${row.description}" />
<br />

<div id = header>
<div id = recipeOut>
<h2 class = "recipe.title"><spring:message code = "recipe.title"/>: ${recipe.title}</h2>

<h3><spring:message code = "recipe.summary"/>: ${recipe.summary}</h3>


<h4><spring:message code = "recipe.likes"/>: ${recipe.likes}</h4>

<h4><spring:message code = "recipe.dislikes"/>: ${recipe.dislikes}</h4>
<br />

<jstl:choose>
	<jstl:when test="${owner}">
	<security:authorize access="hasRole('USER')">
		<input type="button" name="AddCategory"
			value="<spring:message code="recipe.add.category" />"
			onclick="javascript: window.location.replace('recipe/user/listCategory.do?recipeId=${recipe.id}')" />
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<input type="button" name="AddIngredient"
			value="<spring:message code="recipe.add.ingredient" />"
			onclick="javascript: window.location.replace('ingredientLine/user/addIngredientLine.do?recipeId=${recipe.id}')" />
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<input type="button" name="AddStepToCook"
			value="<spring:message code="recipe.add.stepToCook" />"
			onclick="javascript: window.location.replace('recipe/user/addStepToCook.do?recipeId=${recipe.id}')" />
	</security:authorize>

	</jstl:when>
</jstl:choose>
</div>
<div id = stepToCookOut>
<h2><spring:message code = "recipe.stepToCook"/></h2>

<jstl:forEach items="${stepToCooks}"  var = "stepToCook">
	
	<h3><jstl:out value="${stepToCook.number}"/></h3>
	<h4><jstl:out value="${stepToCook.description}"/></h4>
	<h5><jstl:out value="${stepToCook.picture}"/></h5>
	  
</jstl:forEach>
</div>

<div id = IngredientOut>
<h2><spring:message code = "recipe.ingredient"/></h2>

<jstl:forEach items="${ingredients}"  var = "i">
	<h3><jstl:out value="${i.name}"/></h3>

</jstl:forEach>
</div>

<div id = IngredientOut>
<h2><spring:message code = "recipe.quantity"/></h2>
<jstl:forEach items="${ingredientLines}"  var = "il">
	<h4><jstl:out value="${il.multiplicity}"/>&nbsp;&nbsp;<jstl:out value="${il.unit}"/></h4>
</jstl:forEach>
</div>

<div id = IngredientOut>
<h2><spring:message code = "recipe.categories"/></h2>
<jstl:forEach items="${categories}"  var = "c">
	<h4><jstl:out value="${c.name}"/></h4>
</jstl:forEach>
</div>

<br/>
<div id = mailboxCommonButtons>
<h2 class="comment"><spring:message code="recipe.comments"/></h2>

<display:table name="comments" id="comment" pagesize="5" class="displaytag">
	
	<spring:message code="comment.title" var="titleHeader" /> 
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="comment.text" var="textHeader" /> 
	<display:column property="text" title="${textHeader}" />
	
	<spring:message code="comment.stars" var="starsHeader" /> 
	<display:column property="stars" title="${starsHeader}" />
	
</display:table>

<security:authorize access = "hasRole('USER')">
	<a href="comment/create.do?recipeId=${recipe.id}">
		<spring:message code="recipe.writeComment" />
	</a>
</security:authorize>

<security:authorize access = "hasRole('NUTRITIONIST')">
	<a href="comment/create.do?recipeId=${recipe.id}">
		<spring:message code="recipe.writeComment" />
	</a>
</security:authorize>

<br />
	
	<input type="button" name="cancel"
	value="<spring:message code="recipe.back" />"
	onclick="history.go(-1)" />
</div>
</div>