<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<div id="form-wrapper" class="myform">
	<div class="divCenter">
<form:form id="form" action="census/edit.do" modelAttribute="census">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="token_propietario" />
	<form:hidden path="voto_por_usuario"/>
	<form:hidden path="votacion_id"/>
	
	
	
	<acme:textbox code="census.name" path="name"/>
	
	<acme:submit name="save" code="census.save"/>

</form:form>

<acme:cancel url="welcome/index.do" code="census.back"/>
	
	
	</div>
</div>


