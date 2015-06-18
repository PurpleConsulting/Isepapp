<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">
		Accueil
		<small> - <c:out value="${sessionScope.user.getPseudo()}"></c:out> - </small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 group">
		<h4>- Les groupes -</h4>
		<em style="color:#337AB7">Ajouter un groupe...</em>
		<form class="form-inline" method="post" action="AlterGroups">
		  <div class="form-group">
		    <div class="input-group">
		      <div class="input-group-addon">G</div>
		      	<input type="text" name="new_grp" class="form-control" id="inputgroup" placeholder="1A">
		    </div>
		  </div>
		  <div class="form-group">
			  <select name="new_grp_tutor" class="form-control selectpicker" title="(Vide)" data-header="Selectionnez un Tuteur">
				<option value="(vide)">Sans tuteur</option>
				<c:forEach var="tutor" items="${teachers}" varStatus="status">
						<option ${tutor.getPseudo() != group.getTutor() ? '' : 'selected'}
						 value="${tutor.getPseudo()}">
							<c:out value="${tutor.getFirstName()}"></c:out>
							<c:out value="${tutor.getLastName()}"></c:out>
						</option>
				</c:forEach>
			  </select>
			</div>
			<div class="form-group">
		  		<button type="submit" class="btn btn-default"><span class="fa fa-plus"> </span> Ajouter un groupe</button>
		  	</div>
		  	<div class="form-group">
		  		<a class="btn btn-default" href="Tutors" role="button"><span class="fa fa-plus"> </span>  Ajouter un Tuteur</a>
		  	</div>
		</form>
		<hr/>
		<div class="latest-group">
			<div class="alert alert-success"> Groupe G4C - Nombre de note: 0  - Absences: 0  - les livrables(0) - tuteur: ldivad</div>
			<div class="alert alert-success"> Groupe G4C - Nombre de note: 0  - Absences: 0  - les livrables(0) - tuteur: ldivad</div>
			<div class="alert alert-success"> Groupe G4C - Nombre de note: 0  - Absences: 0  - les livrables(0) - tuteur: ldivad</div>
			<a href="Promo"><em>Voir plus de group...</em></a>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 numbers">
		<h4>- Les chiffres -</h4>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 semester">
		<h4>- Organisation du semestre -</h4>
		<div class="col-xs-12">
		<div class="col-xs-12 alert alert-info" style="display: none">
		</div>
		</div>
		<div class="col-xs-12">
			<form class="subject_file" action="FileHandler" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label>Ajoutez un sujet d' APP pour ce semestre.</label>
					<div class="progress" style="display: none">
						<div class="progress-bar progress-bar-striped active"
							 role="progressbar" style="width: 0%">
						</div>
					</div>
					<input type="file" name="subject_file" id="input_subject" class="input-class file-loading"  data-show-preview="false" />
				</div>
			</form>
		</div>
		<div class="col-xs-12">
			<form class="promo_file" action="FileHandler" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label>Ajoutez une liste d'étudiants. </label> <a href="#" id="csv-reminder"> Revoir le format accepté</a>
					<div class="progress" style="display: none">
						<div class="progress-bar progress-bar-striped active"
							 role="progressbar" style="width: 0%">
						</div>
					</div>
					<input type="file" name="promo_file" id="input_promo" class="input-class"  data-show-preview="false" />
				</div>
			</form>
		</div>
		<div class="col-xs-12">
			<form class="backup_file" action="FileHandler" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label>Importer une sauvegarde. </label> <a href="#" id="backup-reminder"> Revoir contenu d'une sauvegarde</a>
					<div class="progress test" style="display: none">
						<div class="progress-bar progress-bar-striped active"
							 role="progressbar" style="width: 0%">
						</div>
					</div>
					<input type="file" name="backup_file" id="input_backup" class="input-class"  data-show-preview="false" />
				</div>
			</form>
		</div>
		<div class="col-xs-12">
			<div class="btn btn-default">
				Réaliser une sauvegarde
			</div>
			<div class="btn btn-danger">
				Archivez le semestre.
			</div>
		</div>
	</div>
</div>