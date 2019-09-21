<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('AUDITOR')">

	<div class="col-md-6 col-centered">
		<div class="well bs-component">

			<form:form action="audit/auditor/edit.do"
				modelAttribute="audit" class="form-horizontal" method="post">

				<fieldset>
					

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="draft" />
					<form:hidden path="moment" />
					<form:hidden path="property" />
					<form:hidden path="auditor" />
					
				
					
					<acme:textbox code="audit.attachments" path="attachments" placeHolderCode="audit.attachments.ph"/>
					<acme:textarea code="audit.text" path="text"/>
									
				


					<div class="form-group">
						<div class="col-md-12">

							<input type="submit" name="save"
								value="<spring:message code="audit.save" />"
								onclick="return confirm('<spring:message code = "audit.confirm.save"/>')"/>
								
							<button type="submit" name="saveAsDraft"
								class="btn btn-raised btn-primary">
								<spring:message code="audit.saveAsDraft" />
							</button>

							<input type="button" name="cancel"
							value="<spring:message code="property.cancel" />"
							onclick="javascript: history.back()" />
	
							<jstl:if test="${audit.getId() != 0}">

								<button type="submit" name="delete"
									class="btn btn-raised btn-primary">
									<spring:message code="audit.delete" />
								</button>

							</jstl:if>
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>

</security:authorize>