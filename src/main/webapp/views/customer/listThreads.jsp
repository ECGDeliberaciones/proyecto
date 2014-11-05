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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="customer.deliberations.title" var="title"></spring:message>
<spring:message code="customer.deliberations.author" var="author"/>
<spring:message code="customer.deliberations.date" var="date"/>
<display:table name="threads" id="row"

requestURI="customer/listThreads.do"
pagesize="5" class="displaytag" >


<display:column title="${title}">

<a href="customer/seeThread.do?id=${row.id }"><jstl:out value="${row.title }"></jstl:out></a>
</display:column>
<display:column title="${author }">
<jstl:out value="${row.user.name }"></jstl:out>
</display:column>
<display:column title="${date }"><jstl:out value="${row.creationMoment }"></jstl:out></display:column>

 

</display:table>