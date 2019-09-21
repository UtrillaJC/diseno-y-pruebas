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
<security:authorize access="hasRole('ADMINISTRATOR')">
	<jstl:choose>
		<jstl:when test = "${promoted == false}">
			<h2><spring:message code="masterClass.isDemote"/></h2>
		</jstl:when>
		<jstl:when test = "${promoted == true}">	
			<h2><spring:message code="masterClass.isPromote"/></h2>
		</jstl:when>
	</jstl:choose>	
</security:authorize>

<security:authorize access="hasRole('USER')">
<jstl:choose>
	<jstl:when test="${registered == true}">
		<h2><spring:message code="masterClass.isRegister" /></h2>
	</jstl:when>

	<jstl:when test="${registered == false}">
		<h2><spring:message code="masterClass.isNotRegister" /></h2>
	</jstl:when>
</jstl:choose>
</security:authorize>	
	
<display:table name = "masterClasses" id = "row" requestURI ="${requestURI}" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "masterClass.title" var = "titleHeader" />
	<display:column property = "title" title = "${titleHeader}" sortable = "true"/>
	
	<spring:message code = "masterClass.description" var = "descriptionHeader" />
	<display:column property = "description" title = "${descriptionHeader}" sortable = "false"/>
	
	<spring:message code = "masterClass.cook" var = "cookHeader" />
	<display:column property = "cook.name" title = "${cookHeader}" sortable = "false"/>
	
	<security:authorize access="hasRole('USER')">
	<jstl:choose>
		<jstl:when test = "${registered == true}">
			<display:column>
				<a href="masterClass/user/unregister.do?masterClassId=${row.id}"
				onclick="return confirm('<spring:message code = "masterClass.confirm.unregister"/>')">
					<spring:message code="masterClass.unregister"/>
					
				</a>
			</display:column>	
			<display:column>	
				<a href="learningMaterial/user/list.do?masterClassId=${row.id}">
					<spring:message code="masterClass.learningMaterial.list"/>
				</a>
			</display:column>	
		</jstl:when>
		<jstl:when test = "${registered == false}">
			<display:column>
				<a href="masterClass/user/register.do?masterClassId=${row.id}">
					<spring:message code="masterClass.register"/>
				</a>
			</display:column>	
			
			<security:authorize access = "isAuthenticated()">
				<display:column>
			
						<a href="learningMaterial/list.do?masterClassId=${row.id}">
							<spring:message code="masterClass.learningMaterial.list" />
						</a>
				
				</display:column>
			</security:authorize>
		</jstl:when>
	</jstl:choose>	
	</security:authorize>
	<security:authorize access = "hasRole('COOK')">
		
		<display:column>
		
			<a href="masterClass/cook/edit.do?masterClassId=${row.id}">
				<spring:message code="masterClass.edit" />
			</a>
		
		</display:column>
	</security:authorize>
	
	<security:authorize access = "hasRole('COOK')">
		
		<display:column>
		
			<a href="text/cook/edit.do?masterClassId=${row.id}">
				<spring:message code="masterClass.addText" />
			</a>
		
		</display:column>
	</security:authorize>
	
	<security:authorize access = "hasRole('COOK')">
		
		<display:column>
		
			<a href="presentation/cook/edit.do?masterClassId=${row.id}">
				<spring:message code="masterClass.addPresentation" />
			</a>
		
		</display:column>
	</security:authorize>
	
	<security:authorize access = "hasRole('COOK')">
		
		<display:column>
		
			<a href="video/cook/edit.do?masterClassId=${row.id}">
				<spring:message code="masterClass.addVideo" />
			</a>
		
		</display:column>
	</security:authorize>
	

	<security:authorize access="hasRole('ADMINISTRATOR')">
	<jstl:choose>
		<jstl:when test = "${promoted == true}">
			<display:column>
				<a href="masterClass/administrator/demote.do?masterClassId=${row.id}">
					<spring:message code="masterClass.demote"/>
				</a>
			</display:column>	
		</jstl:when>
		
		<jstl:when test = "${promoted == false}">
			<display:column>
				<a href="masterClass/administrator/promote.do?masterClassId=${row.id}">
					<spring:message code="masterClass.promote"/>
				</a>
			</display:column>	
		</jstl:when>
		
	</jstl:choose>	
	</security:authorize>


</display:table>



<security:authorize access = "hasRole('COOK')">
	<input type="button" name="create"
	  value="<spring:message code="masterClass.create" />"
	  onclick="javascript: window.location.replace('masterClass/cook/create.do')" />
</security:authorize>

<input type="button" name="cancel"
  value="<spring:message code="masterClass.back" />"
  onclick="javascript: window.location.replace('welcome/index.do')" />