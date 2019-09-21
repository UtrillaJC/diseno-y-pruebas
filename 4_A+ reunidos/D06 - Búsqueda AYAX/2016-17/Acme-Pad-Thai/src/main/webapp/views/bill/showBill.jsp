<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<h2 class = "sponsor.name"><spring:message code = "sponsor.name"/>: ${sponsor.name}</h2>

<a><spring:message code = "bill.totalCost"/>: ${getCostTotal}</a>
<br />
<br />
	
<input type="button" name="cancel"
	value="<spring:message code="bill.back" />"
	onclick="javascript: window.location.replace('bill/administrator/list.do')" />
