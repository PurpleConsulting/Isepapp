<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10 titlepage">
		Création de la grille de compétences.
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 skills-selector">
		<select class="selectpicker" data-target="${support != null ? support : '-2'}">
			<option value="-2">Les compétences</option>
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<option value="${skill.getId()}"><c:out value="${skill.getTitle()}"></c:out></option>
			</c:forEach>
		</select>
		<a class="btn btn-default" href="#" role="button">
			<span class="fa fa-plus"> </span>  Ajouter une compétence
		</a>
		<form class="form-inline">
			<div class="form-group">
				<input class="form-control" type="text" placeholder="Sous compétence"/>
			</div>
		</form>
	</div>
</div>
<hr class="col-xs-offset-1 col-xs-10" />
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 skills-editors">
		<c:forEach var="skill" items="${skills}" varStatus="status">
			<form method="post" action="ManageSkills?skill_id=${skill.getId()}" class="edit" id="form-skill${skill.getId()}">
				<div class="skill-group form-group">
					<input name="skill_title" value="${skill.getTitle()}" class="form-control  col-xs-offset-0 skill" data-ref="${skill.getId()}"/>
					<c:if test="${skill.getId() != 0 }">
					<span class="fa fa-times-circle fa-2x"></span>
					</c:if>
				</div>
				<div class="input-group">
					<div class="input-group-addon">Sous-titre:</div>
					<input name="skill_subtitle" value="${skill.getSubtitle()}" class="form-control  col-xs-offset-0 subskill"/>
				</div>
					<c:forEach var="subSkill" items="${skill.getSub_skills()}" varStatus="status">
					<div class="subskill-group form-group input-group ">
						<div class="input-group-addon plus"><span class="fa fa-plus"></span></div>
						<input name="sub_skill_title${subSkill.getId()}" value="${subSkill.getTitle()}" data-ref="${subSkill.getId()}" class="form-control col-xs-offset-0 subskill"/>
						<span class="fa fa-times-circle-o  fa-2x"></span>
					</div>
				</c:forEach>
				<button type="submit" class="btn btn-default subskill_adder"><span class="fa fa-plus"> </span> Sous compétence</button>
				<button type="submit" class="btn btn-default"><span class="fa fa-pencil-square-o"> </span> Modifier</button>
			</form>
		</c:forEach>
	</div>
</div>