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

<spring:message code="administrator.numberOfCabbages" var="number"/>
<spring:message code="administrator.name" var="name"/>
<spring:message code="administrator.numberOfCabbages" var="cabbagesCode"/>
<spring:message code="administrator.title" var="title"/>



<jstl:out value="${number }-----> ${size }"></jstl:out>


<display:table name="customers" id="row"

requestURI="administrator/dashboard.do"
pagesize="5" class="displaytag" >

<display:column title="${name }"> <jstl:out value="${row.name }"/></display:column>
<display:column title="${cabbagesCode }">


<jstl:forEach items="${row.cabbages}" var="x">

<jstl:out value="${title }---->${x.title }"></jstl:out><br>

</jstl:forEach>

<jstl:out value="${cabbagesCode }---->${row.cabbages.size() }"></jstl:out><br>

</display:column>



</display:table>