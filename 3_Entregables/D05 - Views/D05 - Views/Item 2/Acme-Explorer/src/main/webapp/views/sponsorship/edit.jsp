<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="sponsorship/sponsor/edit.do" modelAttribute="sponsorship">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sponsor"/>
	<form:hidden path="trips"/>
	
	<form:label path="banner"><spring:message code="sponsorship.banner"/></form:label>
	<form:input path="banner"/>
	<form:errors path="banner" cssClass="error"/>
	<br/>
	
	<form:label path="information"><spring:message code="information.banner"/></form:label>
	<form:input path="information"/>
	<form:errors path="information" cssClass="error"/>
	<br/>
	
							<!-- CreditCard information -->
	
	<form:label path="holder"><spring:message code="creditCard.holder"/></form:label>
	<form:input path="holder"/>
	<form:errors path="holder" cssClass="error"/>
	<br/>
	
	<form:label path="brand"><spring:message code="creditCard.brand"/></form:label>
	<form:input path="brand"/>
	<form:errors path="brand" cssClass="error"/>
	<br/>
	
	<form:label path="number"><spring:message code="creditCard.number"/></form:label>
	<form:input path="number"/>
	<form:errors path="number" cssClass="error"/>
	<br/>
	
	<form:label path="expirationMonth"><spring:message code="creditCard.expirationMonth"/></form:label>
	<form:input path="expirationMonth"/>
	<form:errors path="expirationMonth" cssClass="error"/>
	<br/>
	
	<form:label path="expirationYear"><spring:message code="creditCard.expirationYear"/></form:label>
	<form:input path="expirationYear"/>
	<form:errors path="expirationYear" cssClass="error"/>
	<br/>
	
	<form:label path="cvv"><spring:message code="creditCard.cvv"/></form:label>
	<form:input path="cvv"/>
	<form:errors path="cvv" cssClass="error"/>
	<br/>
									<!-- Buttons -->
	<input type="submit" name="save" value="<spring:message code="sponsorship.save"/>" />
	<jstl:if test="${sponsorship.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="sponsorship.delete"/>"/>
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="sponsorship.cancel" />" 
			onclick="javascript: relativeRedir('sponsorship/list.do');" />
	
</form:form>