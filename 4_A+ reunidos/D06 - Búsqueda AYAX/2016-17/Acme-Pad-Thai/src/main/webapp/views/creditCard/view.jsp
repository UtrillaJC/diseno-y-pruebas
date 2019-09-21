<%--
 * view.jsp
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


<h2 class = "creditCardNumber"><spring:message code = "creditCard.number"/>: ${creditCard.number}</h2>


<h3 class = "creditCardHolderName"><spring:message code = "creditCard.holderName"/>: ${creditCard.holderName}</h3>

<a><spring:message code = "creditCard.brandName"/>: ${creditCard.brandName}</a>
<br />
<br />
<a><spring:message code = "creditCard.expirationMonth"/>: ${creditCard.expirationMonth}</a>
<br />
<br />
<a><spring:message code = "creditCard.expirationYear"/>: ${creditCard.expirationYear}</a>
<br />
<br />
<a><spring:message code = "creditCard.cvv"/>: ${creditCard.cvv}</a>
<br />
<br />
<input type="button" name="modify" 
	value=" <spring:message code="creditCard.edit" />"
onclick="javascript: window.location.replace('creditCard/sponsor/edit.do?creditCardId=${creditCard.id}')" />
&nbsp;
<input type="button" name="cancel"
	value="<spring:message code="creditCard.cancel" />"
	onclick="javascript: window.location.replace('welcome/index.do')" />
	



