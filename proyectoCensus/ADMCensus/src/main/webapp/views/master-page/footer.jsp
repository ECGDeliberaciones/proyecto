<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<jsp:useBean id="date" class="java.util.Date" />


<div id="copyright" class="container">
	<p>
		Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> AgoraUs Co., Inc. | Cracion/Administracion de censos | 
		
	</p>
</div>