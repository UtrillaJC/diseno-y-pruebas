<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('SPONSOR')">
	<jstl:choose>
		<jstl:when test = "${paid == false}">
			<h2><spring:message code="bill.unpaid"/></h2>
		</jstl:when>
		<jstl:when test = "${paid == true}">	
			<h2><spring:message code="bill.paid"/></h2>
		</jstl:when>
	</jstl:choose>	
</security:authorize>

<display:table name = "bills" id = "row" requestURI = "${requestURI }"  pagesize = "5" class = "displaytag">
	
	<spring:message code="bill.momentCreated" var="momentCreatedHeader" />
	<display:column property="momentCreated" title="${momentCreatedHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
	
	<spring:message code="bill.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader}" sortable="false" />
	
	<spring:message code="bill.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="bill.sponsorName" var="sponsorNameHeader" />
	<display:column property="campaign.sponsor.name" title="${sponsorNameHeader}" sortable="false" />
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<a href="bill/administrator/showBill.do?sponsorId=${row.campaign.sponsor.id}">
				<spring:message code="bill.showCostTotal" />
			</a>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('SPONSOR')">
		<jstl:choose>
			<jstl:when test = "${paid == true}">
				<spring:message code="bill.momentPaid" var="momentPaidHeader" />
				<display:column property="momentPaid" title="${momentPaidHeader}"
					format="{0,date,dd/MM/yyyy HH:mm}" sortable="false" />
			</jstl:when>
			<jstl:when test = "${paid == false}">
				<spring:message code="bill.momentPaid" var="momentPaidHeader" />
				<display:column title="${momentPaidHeader}">
					<a href="bill/sponsor/pay.do?billId=${row.id}">
						<spring:message code="bill.momentPaid.list"/>
					</a>
				</display:column>	
			</jstl:when>
		</jstl:choose>
	</security:authorize>
			
</display:table>
<br/>	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<jstl:choose>
			<jstl:when test = "${paid == false}">
				<input type="button" name="sendBulkMessage"
					value="<spring:message code="send.bulkMessage" />"
					onclick="javascript: window.location.replace('bill/administrator/sendMessage.do')" />	
			</jstl:when>
		</jstl:choose>
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="campaign.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />		
