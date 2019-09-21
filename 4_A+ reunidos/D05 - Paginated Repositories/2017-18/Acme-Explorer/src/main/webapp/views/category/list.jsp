<%-- list.jsp de Category --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="categories" id="row" requestURI="category/list.do" pagesize="5" class="displaytag">

	<div>
		<security:authorize access="hasRole('ADMIN')">
			<display:column>
				<spring:message var="categoryEdit" code="category.edit"/> 
				<a href="category/admin/edit.do?categoryId=${row.id}"> <spring:message code="categoryEdit"/> </a>
			</display:column>
		</security:authorize>
		
		<display:column>
			<spring:message var="categoryTrip" code="category.trip"/>
			<a href="trip/list.do?categoryId=${row.id}"> <spring:message code="categoryTrip"/> </a>
		</display:column>
		
		
		<spring:message var="categoryParentCategory" code="category.parentCategory"/>
		<display:column property="parentCategory" title="${categoryParentCategory}"/>
		
		<spring:message var="categoryName" code="category.name"/>
		<display:column property="name" title="${categoryName}" />
	</div>
</display:table>

<security:authorize access="hasRole('ADMIN')">
	<spring:message var="categoryCreate" code="category.create"/> 
	<a href="category/admin/create.do"> ${categoryCreate}</a>
</security:authorize>