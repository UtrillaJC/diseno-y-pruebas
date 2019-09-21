<%--
 * layout.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="shortcut icon" href="favicon.ico"/> 

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jquery.cookie.js"></script>
<script type="text/javascript" src="scripts/jmenu.js"></script>
<!-- Accordion -->
<script src="scripts/accordion.js"></script>

<link rel="stylesheet" href="styles/common.css" type="text/css">
<link rel="stylesheet" href="styles/jmenu.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">
<link rel="stylesheet" href="styles/jquery-ui.css" type="text/css">



<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- core CSS of SnackbarJS -->
<link href="styles/snackbar.min.css" rel="stylesheet">
<!-- the default theme of SnackbarJS -->
<link href="styles/material.css" rel="stylesheet">
<!-- Mostrar confirmación -->
<script type="text/javascript">
	function relativeRedir(loc) {
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
			if (b[0].href.substr(b[0].href.length - 1) == '/'
					&& loc.charAt(0) == '/')
				loc = loc.substr(1);
			loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}

	function hiddenOrShow() {
		var elemento = document.getElementById("alerta");
		if (elemento.style.display == "none") {
			elemento.style.display = "block";
		} else {
			elemento.style.display = "none";
		}
	}

	function hiddenOrShowById(id) {
		var elemento = document.getElementById(id);
		if (elemento.style.display == "none") {
			elemento.style.display = "block";
		} else {
			elemento.style.display = "none";
		}
	}

	function hiddenOrShowByClass(id) {
		var elemento = $('.' + id);
		if (elemento.css("display") == "none") {
			elemento.css("display", "block");
		} else {
			elemento.css("display", "none");
		}
	}
</script>
<!-- Accordion -->
<script src="scripts/accordion.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#jMenu").jMenu();
			
		if($.cookie('showCookies')=='yes')
	        $("#cookies").show();
	    if($.cookie('showCookies')=='no')
	    	$("#cookies").hide();
	    
	    $("#cookiesClose").click(function(){
            var displayStatus = $("#cookies").css('display');
            
            switch(displayStatus){
                case 'none':    $("#cookies").show();
                                $.cookie('showCookies', 'yes', {expires : 1});
                    break;
                case 'block':   $("#cookies").hide();
                                $.cookie('showCookies', 'no', {expires : 1});
                    break;
            }
            
    	});
	    
	    
	    $("#termsLink").click(function(){ 
	    $( "#terms" ).dialog({
	        modal: true,
	        buttons: {
	          Ok: function() {
	            $( this ).dialog( "close" );
	          }
	        },
	      width:"70%",
	      maxWidth: "700px"
	    });
	    });
	  		
	});

	function askSubmission(msg, form) {
		if (confirm(msg))
			form.submit();
	}
</script>

</head>

<body>

	<tiles:insertAttribute name="terms" />

	<div>
		<tiles:insertAttribute name="header" />
	</div>
	<div>
		<h1>
			<tiles:insertAttribute name="title" />
		</h1>
		<tiles:insertAttribute name="body" />	
		<jstl:if test="${message != null}">
			<br />
			<span class="message"><spring:message code="${message}" /></span>
		</jstl:if>	
	</div>
	<div>
		<tiles:insertAttribute name="footer" />
	</div>
		<tiles:insertAttribute name="cookies" />

</body>

</html>