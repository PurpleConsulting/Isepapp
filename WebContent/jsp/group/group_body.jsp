<!--  BODY PART GROUP MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Fiche Groupe
	<small> - G2B</small>
	<a class="btn btn-default" href="#" role="button"><span class="fa fa-pencil"> </span>  Editer</a>
</h1>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 idcard">
		<div class="col-sm-12 label-group">
			<div class="col-sm-offset-0 col-sm-2" ><span class="fa fa-users fa-2x"></span>Groupe G2B</div>
			<div class="col-sm-offset-1 col-sm-3"><span class="fa fa-user fa-2x"></span>Tuteur: <a href="#${group.getTutor()}"><c:out value="${group.getTutor()}"></c:out> </a>.</div>
			<div class="col-sm-offset-0 col-sm-2"><span class="fa fa-file-text fa-2x"></span> Moyenne: <strong> 3.0 </strong></div>
			<div class="col-sm-offset-1 col-sm-2"><span class="fa fa-bed fa-2x"></span> Absences: <strong><c:out value="${fn:length(missings)}"></c:out> </strong></div>
		</div>
		<c:set var="i" scope="request" value="${0}"/>
		<c:set var="abs" scope="request" value="${0}"/>
		<c:forEach var="student" items="${group.getMembers()}" varStatus="status">
			<c:forEach var="miss" items="${missings}" varStatus="status">
				<c:if test="${miss.getStudent() == student.getPseudo()}"><c:set var="abs" value="${abs + 1}"/></c:if>
			</c:forEach>
			<div class="${i%2 == 0 ? '' : 'col-sm-offset-0 col-md-offset-1'} col-md-5 col-sm-12 student-cell">
				<div class="picture">
					<img src="./img/photo.jpg" alt="" />
				</div>
				<div class="col-xs-offset-0 col-xs-12 col-sm-6">
					<span><c:out value="${student.getFirstName()} ${student.getLastName()}"></c:out> - 
					<a href="Students?pseudo=${student.getPseudo()}">
						<em><c:out value="${student.getPseudo()}"></c:out></em>
					</a></span>
					<span><a href="mailto:${student.getMail()}">
						<c:out value="${student.getMail()}"></c:out>
					</a></span>
					<span>Absences: <span class="badge missing"><c:out value="${abs}"></c:out></span> </span>
					<span>Moyenne: <span class="badge mark">10</span></span>
				</div>
			</div>
			<c:set var="i" scope="request" value="${i + 1}"/>
			<c:set var="abs" scope="request" value="${ 0 }"/>
		</c:forEach>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 delivery">
		<h4>Les livrables</h4>
		<div class="col-md-3 col-sm-6" >
			<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Ok: Rendu le 24/03">CR d'avancement</button>
		</div>
		<div class="col-md-3 col-sm-6" >
			<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Ok: Rendu le 02/05">Livrale toto</button>
		</div>
		<div class="col-md-3 col-sm-6" >
			<button type="button" class="btn btn-default" data-toggle="" data-placement="right" title="">Résumé tutu</button>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 groupmark">
		<h4>Les stats</h4>
	</div>
</div>