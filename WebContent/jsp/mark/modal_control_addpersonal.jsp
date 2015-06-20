<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="delete-grp" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Cette section vous permet d'ajouter des notes personnalisées pour dissocier
			des étudiants du reste du group. Pour se faire, veuillez choisir un élève et cocher les 
			copétences sur lequelles vous voullez le noter.
			<select class="selectpicker select-modal">
				<option value="init">Sélectionnez un Etudiant</option>
			</select><br/>
			<label>Les compétences</label>
			<ul>
			</ul>
		</p>
	</div>
</form>