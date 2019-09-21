<%--
 * submit.tag
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty"%>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%-- Attributes --%>

<%-- Attributes --%>

<%@ attribute name="delete" required="true"%>
<%@ attribute name="route" required="true"%>
<%@ attribute name="message" required="true"%>




<%-- Definition --%>

<div class="form-group">
	<div class="col-md-12">
	
		<button type="submit" name="save"
			class="btn btn-raised btn-primary">
			<spring:message code="save" />
		</button>
		
			<input type="button" name="cancel" class="btn btn-raised btn-primary"
				value="<spring:message code="cancel" />"
				onclick="javascript:relativeRedir('${route}');" />

		

		<jstl:if test="${delete}">
				
			<input type="button" name="delete" class="btn btn-raised btn-primary"
				value="<spring:message code="delete" />"
				onclick="hiddenOrShow()" />&nbsp;	
				
				<div id="alerta" class="alert alert-warning"
			style="display: none; width: 400px; height: 90px; margin-right: auto; margin-left: auto;">
			<strong><spring:message code="${message}" /></strong><br> <input
				type="submit" name="delete" class="btn btn-raised btn-success"
				value="<spring:message code="confirm.yes" />" />&nbsp; <input
				type="button" name="delete" class="btn btn-raised btn-danger"
				value="<spring:message code="confirm.no" />"
				onclick="hiddenOrShow();" />&nbsp;
		</div>
		</jstl:if>
	</div>
</div>