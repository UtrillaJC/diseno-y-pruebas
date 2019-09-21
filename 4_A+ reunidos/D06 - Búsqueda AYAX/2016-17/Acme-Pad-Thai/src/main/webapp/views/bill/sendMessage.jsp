<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name = "bills" id = "row" requestURI = "bill/administrator/sendMessage.do"  pagesize = "5" class = "displaytag">
	
	<spring:message code="bill.momentCreated" var="momentCreatedHeader" />
	<display:column property="momentCreated" title="${momentCreatedHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
	
	<spring:message code="bill.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader}" sortable="false" />
	
	<spring:message code="bill.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
</display:table>

	
	
		
