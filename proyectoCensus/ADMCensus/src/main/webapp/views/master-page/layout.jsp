<%--
 * layout.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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

<link href="styles/default.css" rel="stylesheet" type="text/css" media="all" />

<title>
	<tiles:insertAttribute name="title" ignore="true" />
	<jstl:if test="${not empty subtitle}">
		<jstl:out value="${subtitle}" />
	</jstl:if>
</title>

<script type="text/javascript">
		function relativeRedir(loc) {	
			var b = document.getElementsByTagName('base');
			if (b && b[0] && b[0].href) {
	  			if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
	    		loc = loc.substr(1);
	  			loc = b[0].href + loc;
			}
			window.location.replace(loc);
		}
	</script>

</head>

<body>
<div id="contenedor">

<div id="logo">
	<a href="welcome/index.do"><img src="images/logo.jpg" alt="Acme-Adventure Co., Inc." style="width: 300px; height: 200px;" /></a>
	<br /><br />
	<a href="?language=en" title="English"><strong>English</strong></a>&nbsp;|&nbsp;<a href="?language=es"><strong>Español</strong></a>
</div>

<div id="header">
	<div id="menu" class="container">
		<tiles:insertAttribute name="header" />
	</div>
</div>
 
<div id="page-wrapper">
	<div id="page" class="container">
		<div id="content">
			<div class="title">
				<h2>
					<tiles:insertAttribute name="title" />
					<jstl:if test="${not empty subtitle}">
						<jstl:out value="${subtitle}" />
					</jstl:if>
				</h2>
			</div>
			<jstl:if test="${message != null}">
				<span class="message"><spring:message code="${message}" /></span>
				<br />
				<br />
			</jstl:if>
			<p>
				<tiles:insertAttribute name="body" />
			</p>
		</div>
	</div>
</div>
<tiles:insertAttribute name="footer" />

</div>

</body>
</html>