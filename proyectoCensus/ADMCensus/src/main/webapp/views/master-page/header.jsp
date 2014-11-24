<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<div id="cssmenu">
	<ul>
		<security:authorize access="isAnonymous()">
			<!-- <li><a class="fNiv" href="census/create.do"><spring:message code="master.page.census.create" /></a></li>-->	
			<li><a class="fNiv" href="census/list.do"><spring:message code="master.page.census.list" /></a></li>
			<li class='has-sub'><a><spring:message code="master.page.api" /></a>
				<ul>
				
					<li><a target="_blank" href="census/json_one_user.do?votacion_id=1&username=pepe"><spring:message code="master.page.census.user" /></a></li>
					<li><a target="_blank" href="census/json_all.do"><spring:message code="master.page.census.all" /></a></li> 
					<li><a target="_blank" href="census/json_By_User.do?username=pepe"><spring:message code="master.page.census.all.user" /></a></li> 
					<li><a target="_blank" href="census/create.do?idVotacion=100&token_propietario=123456&fecha_inicio=12/11/2014&fecha_fin=12/12/2014"><spring:message code="master.page.census.create" /></a></li>
					<li><a target="_blank" href="census/updateUser.do?censusId=32768&username=pepe"><spring:message code="master.page.census.update.user" /></a></li> 
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>


