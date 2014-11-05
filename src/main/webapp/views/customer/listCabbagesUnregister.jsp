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


<display:table name="cabbages" id="row"

requestURI="customer/ownItemList.do"
pagesize="5" class="displaytag" >


<spring:message code="customer.cabbage.title" var="title"></spring:message>
<spring:message code="customer.cabbage.description" var="description"></spring:message>
<spring:message code="customer.cabbage.url" var="url"></spring:message>


<display:column title="${title }" ><jstl:out value="${row.title }"></jstl:out></display:column>
<display:column title="${ description}"><jstl:out value="${row.description }"></jstl:out></display:column>


</display:table>