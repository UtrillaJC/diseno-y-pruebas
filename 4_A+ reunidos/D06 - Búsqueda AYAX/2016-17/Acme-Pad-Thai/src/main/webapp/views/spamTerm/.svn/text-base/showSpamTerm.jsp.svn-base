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
	


<div>
	<h3>
		<jstl:forEach items="${keywords}" var="keyword">
			<jstl:out value="${keyword}" /><br/>
		</jstl:forEach>
	</h3>
</div>

<input type="button" name="modify" 
	value=" <spring:message code="spamTerm.edit" />"
onclick="javascript: window.location.replace('spamTerm/administrator/edit.do?spamTermId=${spamTermId}')" />
&nbsp;
<input type="button" name="cancel"
	value="<spring:message code="spamTerm.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	