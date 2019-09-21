<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>


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
	
<display:table name = "learningMaterials" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "learningMaterial.title" var = "titleHeader" />
	<display:column property = "title" title = "${titleHeader}" sortable = "true"/>
	
	<spring:message code = "learningMaterial.abstractText" var = "abstractTextHeader" />
	<display:column property = "abstractText" title = "${abstractTextHeader}" sortable = "false"/>

	<jstl:choose>
		<jstl:when test = "${registered == true}">
			<security:authorize access = "hasRole('USER')">	
				<display:column>
					<a href="learningMaterial/user/showLearningMaterial.do?learningMaterialId=${row.id}">
						<spring:message code="learningMaterial.showLearningMaterial" />
					</a>
				</display:column>
			</security:authorize>	
		</jstl:when>
	</jstl:choose>	
	
	<security:authorize access = "hasRole('COOK')">	
		<display:column>
			<a href="masterClass/cook/edit.do?masterClassId=${row.id}">
				<spring:message code="masterClass.edit" />
			</a>
		</display:column>
	</security:authorize>	
	
	
</display:table>
	
<security:authorize access="hasRole('COOK')">
	<a href="learningMaterial/cook/create.do">
		<spring:message	code="learningMaterial.create" /></a>
</security:authorize>
	
<input type="button" name="cancel"
  value="<spring:message code="masterClass.back" />"
  onclick="history.go(-1)" />