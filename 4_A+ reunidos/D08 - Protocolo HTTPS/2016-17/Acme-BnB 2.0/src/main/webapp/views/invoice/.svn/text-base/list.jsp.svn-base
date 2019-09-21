<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" name="invoices" requestURI="${requestURI}" id="invoice">
	
	<spring:message code="invoice.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
	
	<spring:message code="invoice.tenantInformation" var="tenantInformationHeader" />
	<display:column property="tenantInformation" title="${tenantInformationHeader}" sortable="false" />
	
	<spring:message code="invoice.totalAmountDue" var="totalAmountDueHeader" />
	<display:column property="totalAmountDue" title="${totalAmountDueHeader}" sortable="false" />
	
	<display:column>
			<a href="invoice/tenant/display.do?invoiceId=${invoice.id}">
				<spring:message code="invoice.display" />
			</a>
	</display:column>
		
</display:table>

	<input type="button" value="<spring:message code="invoice.back" />"
	onclick="javascript: history.back()" />
	
