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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

		
		<spring:message code="census.name" />
		<jstl:out value="${census.name}"/><br/>
		<spring:message code="census.date" />
		<jstl:out value="${census.date}"/><br/><br/>
	
<br/>

<jstl:if test="${!empty users}">

	<display:table pagesize="5" class="displaytag" name="users" requestURI="${requestURI}" id="row">
		
		<!-- Action links -->
		
		<!-- Attributes -->
		<spring:message code="user.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" sortable="true" />
		
		<spring:message code="census.joins" var="joinsHeader" />
		<display:column title="${joinsHeader}">
			<jstl:if test="${!(fn:contains(census.users, row))}">
				<a href="census/addUser.do?censusId=${census.id}&userId=${row.id}">
					<spring:message	code="user.add" />
				</a>
			</jstl:if>
		</display:column>
		
		
		<spring:message code="census.no.joins" var="noJoinsHeader" />
		<display:column title="${noJoinsHeader}">
			<jstl:if test="${(fn:contains(census.users, row))}">
				<a href="census/removeUser.do?censusId=${census.id}&userId=${row.id}">
					<spring:message	code="census.remove" />
				</a>
			</jstl:if>
		</display:column>
		
		
	</display:table>
	<br/>
	<br/>

</jstl:if>

<acme:cancel url="census/details.do?censusId=${census.id}" code="census.back"/>
