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
	
<display:table name = "campaigns" id = "row" requestURI = "campaign/sponsor/list.do" pagesize = "5" class = "displaytag" >
		
	<spring:message code = "campaign.dateStart" var = "dateStartHeader" />
	<display:column property = "dateStart" title = "${dateStartHeader}" 
		format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code = "campaign.dateEnd" var = "dateEndHeader" />
	<display:column property = "dateEnd" title = "${dateEndHeader}" 
		format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code = "campaign.sponsor" var = "sponsorHeader" />
	<display:column property = "sponsor.name" title = "${sponsorHeader}" />
	
	<security:authorize access="hasRole('SPONSOR')">
	<display:column>
			<a href="campaign/sponsor/edit.do?campaignId=${row.id}">
				<spring:message code="campaign.edit" />
			</a>
	</display:column>
	
	<display:column>
			<a href="banner/sponsor/list.do?campaignId=${row.id}">
				<spring:message code="campaign.banner" />
			</a>
	</display:column>
	
	<display:column>
			<a href="banner/sponsor/create.do?campaignId=${row.id}">
				<spring:message code="banner.add" />
			</a>
	</display:column>
	</security:authorize>
	
<security:authorize access="hasRole('ADMINISTRATOR')">
	<display:column>
			<a href="campaign/administrator/generateMonthlyBill.do?campaignId=${row.id}">
				<spring:message code="campaign.bill.generate" />
			</a>
	</display:column>
</security:authorize>

</display:table>

	<security:authorize access="hasRole('SPONSOR')">
		<input type="button" name="create"
			value="<spring:message code="campaign.create" />"
			onclick="javascript: window.location.replace('campaign/sponsor/create.do')" />
	</security:authorize>
	
	<input type="button" name="cancel"
	value="<spring:message code="campaign.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	