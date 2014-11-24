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
		
		<spring:message code="census.token_propietario" />:
		<jstl:out value="${census.token_propietario}"/><br/>
		<spring:message code="census.votacion_id" />:
		<jstl:out value="${census.votacion_id}"/><br /><hr>
		
		<jstl:forEach var="type" items="${mapa}">
			<spring:message code="user.token"/>: 
			<jstl:out value="${type.key}"/>
			
			<spring:message code="user.value"/>: 
			<jstl:out value="${type.value}"/>
			
			<jstl:if test="${type.value != true}">
			
				<a href="census/removeUser.do?censusId=${census.id}&token=${type.key}"><img src="images/trash.png" style="width: 32px; height: 32px;"></a>

			</jstl:if>
			
			<jstl:if test="${type.value == true}">
			
				<img src="images/pulgar-arriba.png" style="width: 32px; height: 32px;">

			</jstl:if>
			<br />
		</jstl:forEach>
	
<br/>
<% Cookie[] galleta = request.getCookies(); 
	
 %>

<jstl:if test="${empty census.voto_por_usuario}">
	
	<acme:cancel url="census/delete.do?censusId=${census.id }" code="census.delete"/>
	
</jstl:if>

<acme:cancel url="census/edit.do?censusId=${census.id }" code="census.edit"/>

<acme:cancel url="census/list.do" code="census.back"/>

