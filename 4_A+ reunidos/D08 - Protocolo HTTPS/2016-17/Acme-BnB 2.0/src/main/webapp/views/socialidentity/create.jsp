<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="isAuthenticated()">
<div class="col-md-6 col-centered">
	<div class="well bs-component">
		<form:form action="socialidentity/edit.do"
			modelAttribute="socialidentity" class="form-horizontal">
			<fieldset>
				<legend> </legend>

	

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="actor" />
				
				<acme:textbox code="socialIdentity.nick" path="nick" />
				<acme:textbox code="socialidentity.profile" path="profile" />
				<acme:textbox code="socialidentity.socialnetworkname" path="socialNetwork" />

						
				<acme:manageButtons route="actor/display.do" message="si.delete.message" delete="${socialidentity.id!=0}"/>
				
			</fieldset>
		</form:form>
	</div>
</div>
</security:authorize>
