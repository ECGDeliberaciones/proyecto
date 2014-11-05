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



<display:table name="projects" id="row"

requestURI="customer/ownItemList.do"
pagesize="5" class="displaytag" >


<spring:message code="project.title" var="title"></spring:message>
<spring:message code="project.creationDate" var="creationDate" ></spring:message>
<spring:message code="project.catJoint" var="catJoin"></spring:message>

<display:column title="${title }" ><jstl:out value="${row.title }"/></display:column>
<display:column title="${creationDate }"><jstl:out value="${row.creationMoment }"/></display:column>
<display:column title="${catJoin }">




</display:column>



</display:table>