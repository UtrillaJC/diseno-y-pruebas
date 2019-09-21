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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="isAutenticated()">
<display:table name="socialIdentitys" id="rowsocialIdentity"
	requestURI="${requestURI}" pagesize="5"
	class="table table-striped table-hover">
	
	<acme:displayColumn var="nick" code="socialIdentity.nick"/>
	<acme:displayColumn var="socialNetwork" code="socialidentity.homepage"/>
	<acme:displayColumn var="socialNetworkName" code="socialidentity.socialnetworkname"/>
	
	<spring:message code="socialidentity.delete" var="delete"></spring:message>
	<display:column title="${delete}">
	
		
	<form action="socialidentity/edit.do" method="post">
		<input type="hidden" value="delete" name="delete">
		<input type="hidden" value="${rowsocialIdentity.id}" name="si_id" id="si_id">
		
		<button style=" background-color: Transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;outline:none;" type="button" onclick="hiddenOrShowById(${rowsocialIdentity.id})">
		<i class="material-icons">delete</i>
		</button>
		<acme:messageDeleteByIdMD message="si.delete.message" id="${rowsocialIdentity.id}"/>
	
	</form>
	
	
	
	</display:column>


</display:table>

<form action="socialidentity/create.do" method="get">
		<button style=" background-color: Transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;outline:none;">
		<i class="material-icons">create</i>
		</button>
	
	</form>
</security:authorize>