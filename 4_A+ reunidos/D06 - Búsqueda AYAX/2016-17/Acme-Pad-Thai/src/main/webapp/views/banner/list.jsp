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

<display:table name = "banners" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
	
	<spring:message code = "banner.description" var = "descriptionHeader" />
	<display:column property = "description" title = "${descriptionHeader}" sortable = "true"/>
	
	<spring:message code = "banner.maxNumDisplayed" var = "maxNumDisplayedHeader" />
	<display:column property = "maxNumDisplayed" title = "${maxNumDisplayedHeader}" sortable = "false"/>

	<spring:message code = "banner.numDisplayed" var = "numDisplayedHeader" />
	<display:column property = "numDisplayed" title = "${numDisplayedHeader}" sortable = "false"/>
	
	
</display:table>
<br/>
<input type="button" name="cancel"
value="<spring:message code="banner.cancel" />"
onclick="javascript: window.location.replace('campaign/sponsor/list.do')" />
