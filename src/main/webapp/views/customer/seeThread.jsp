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
<spring:message code="customer.deliberations.author" var="author"></spring:message>
<spring:message code="customer.deliberations.date" var="date"></spring:message>
<spring:message code="customer.deliberations.comments" var="comments"></spring:message>
<spring:message code="customer.deliberations.text" var="text"></spring:message>
<spring:message code="customer.deliberations.edit" var="edit"></spring:message>




<display:table name="hilo" id="row"

requestURI="customer/seeThread.do"
pagesize="5" class="displaytag" >

<display:column title="${date}"><jstl:out value="${row.creationMoment }"></jstl:out></display:column>

<display:column title="${title}"><jstl:out value="${row.title }"></jstl:out></display:column>
<display:column title="${text }"> <jstl:out value="${row.text }"></jstl:out></display:column>
<display:column title="${edit }"><a href="customer/editThread.do?id=${row.id }">${edit }</a></display:column>

</display:table>


<display:table name="comments" id="row"

requestURI="customer/seeThread.do"
pagesize="5" class="displaytag" >

<display:column title="${date}"><jstl:out value="${row.creationMoment }"></jstl:out></display:column>

<display:column title="${author}"><jstl:out value="${row.user.name }"></jstl:out></display:column>
<display:column title="${text }"> <jstl:out value="${row.text }"></jstl:out></display:column>



</display:table>


<form:form action="customer/saveComment.do" method="post" modelAttribute="comment">

<form:hidden path="id"/>
<form:hidden path="version"/>	
<form:hidden path="user"/>
<form:hidden path="creationMoment" />
<form:hidden path="thread"/>


<acme:textbox code="customer.deliberations.text" path="text"/>
<acme:submit name="save" code="customer.submit"/>





</form:form>