<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1 class="col-xs-offset-1 col-xs-10 titlepage">
	Les compétences. <span class="master-dropdown fa fa-angle-double-down"></span>
	<c:if test="${user.getPosition() == 'respo'}">
		<a class="btn btn-default" href="ManageSkills" role="button">
			<span class="fa fa-pencil"> </span> Editer
		</a>
	</c:if>
</h1>
<c:if test="${fn:length(skills) == 0}">
	<div class="row">
		<div class="col-xs-offset-2 col-xs-8 ">
			<img src="img/empty/skill.svg" alt="" />
		</div>
	</div>
</c:if>
<c:forEach var="skill" items="${skills}" varStatus="status">
	<div class="row">
		
		<div class="col-xs-offset-2 col-xs-9 col-sm-8 alert alert-skill">
			<span class="dropdown fa fa-chevron-circle-down"></span> <c:out value="${skill.getTitle()}"></c:out>
		</div>
		<div class="col-xs-offset-2 col-sm-offset-3 col-xs-9 col-sm-7 alert alert-sub-skill-box">
			<!-- <div class="col-xs-12 skill-checkpoint">some random text ... </div> -->
			<c:forEach var="subSkill" items="${skill.getSubSkills()}" varStatus="status">
				<div class="col-xs-12 alert alert-sub-skill">
					<c:out value="${subSkill.getTitle()}"></c:out>
				</div>
			</c:forEach>
		</div>
	</div>
</c:forEach>

