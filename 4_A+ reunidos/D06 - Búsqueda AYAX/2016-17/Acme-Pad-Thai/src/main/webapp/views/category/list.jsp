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
	
<display:table name = "categories" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
		
	
	<spring:message code = "category.name" var = "nameHeader" />
	<display:column property = "name" title = "${nameHeader}" />
	
	<spring:message code = "category.description" var = "descriptionHeader" />
	<display:column property = "description" title = "${descriptionHeader}" />
	
<security:authorize access="hasRole('USER')">
	<display:column>	
			<a href="recipe/user/addCategory.do?categoryId=${row.id}&recipeId=${recipeId}">
				<spring:message code="category.add" />
			</a>
	</display:column>
</security:authorize>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<display:column>	
			<a href="category/administrator/edit.do?categoryId=${row.id}">
				<spring:message code="category.edit" />
			</a>
	</display:column>
	
<jstl:if test="${listParent == false}">
	<jstl:choose>
		<jstl:when test="${row.parent == null }">
			<display:column>	
					<a href="category/administrator/list-addCategoryParent.do?categoryParentId=${row.id}">
						<spring:message code="category.category.parent" />
					</a>
			</display:column>
		</jstl:when>
	</jstl:choose>
</jstl:if>
<jstl:if test="${listParent == true}">
	<display:column>	
			<a href="category/administrator/addCategoryParent.do?categoryId=${row.id}&categoryParentId=${categoryParentId}">
				<spring:message code="category.add" />
			</a>
	</display:column>
</jstl:if>
	
</security:authorize>	
</display:table>

<security:authorize access = "hasRole('ADMINISTRATOR')">
		<a href="category/administrator/create.do">
			<spring:message code="category.create" />
		</a>
</security:authorize>	
	
	<input type="button" name="cancel"
	value="<spring:message code="category.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	