<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" name="requestsOwn" requestURI="${requestURI}" id="rowRequest">
	
	<!-- Attributes -->

	<spring:message code="request.checkInDate" var="checkInDate" />
	<display:column property="checkInDate" title="${checkInDate}" sortable="false" />
		

	<spring:message code="request.checkOutDate" var="checkOutDate" />
	<display:column property="checkOutDate" title="${checkOutDate}"
		sortable="false" />
		
	<spring:message code="request.smoker" var="smoker" />
	<display:column property="smoker" title="${smoker}"
		sortable="false" />

	<spring:message code="request.creditCard" var="creditCard" />
	<display:column property="creditCard.number" title="${creditCard}"
		sortable="false" />
		
	<spring:message code="request.status" var="status" />
	<display:column property="status" title="${status}"
		sortable="false" />
		
	<spring:message code="request.property" var="property" />
	<display:column property="property.name" title="${property}"
		sortable="false" />
		
	
	<display:column>
		<jstl:if test="${rowRequest.status == 'ACCEPTED' && rowRequest.invoice == null}">
			<a href="invoice/tenant/create.do?requestId=${rowRequest.id}">
				<spring:message code="request.invoice" />
			</a>
		</jstl:if>
	</display:column>

	</display:table>
	
	<input type="button" value="<spring:message code="request.back" />"
	onclick="javascript: history.back()" />
	
