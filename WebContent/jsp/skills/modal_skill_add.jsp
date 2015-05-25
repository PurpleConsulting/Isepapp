<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form action="" class="form-horizontal form-add-skill" id="delete-std" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Dans cette section il vous ai possible d'ajouer une nouvelle compétence pour l'app en cours.
			Après avoir inscrit cette famille de compétences vous serez redirigé pour y ajouter des sous-compétences.
			Dès que ces actions serront terminées les tuteurs pouront noter les étudiants sur ces compétences.
		</p>
	</div>
	<div class="form-group" style="margin-left:10%; width:80%">
		<p><label for="new_skill_title">Nouvelle compétence:</label></p>
		<input id="new_skill_title" name="" class="col-xs-10 form-control" type="text" placeholder="Conception / réalisation" style="margin-bottom:6px;"/>
		<p><label for="new_skill_subtitle">Sous-titre de compétence:</label></p>
		<input id="new_skill_subtitle"name="" class="col-xs-10 form-control" type="text" placeholder="Concevoir et réaliser une application informatique (site web)"/>
	</div>
</form>