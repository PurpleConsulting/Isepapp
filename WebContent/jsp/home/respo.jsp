<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">
		Accueil
		<small data-target="${user.getPseudo()}"> - <c:out value="${sessionScope.user.getPseudo()}"></c:out> - </small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 group">
		<h4>- Les groupes -</h4>
		<em style="color:#337AB7">Ajouter un groupe ...</em>
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
		  		<a class="btn btn-default" href="Tutors" role="button"><span class="fa fa-plus"> </span>  Ajouter un tuteur</a>
		  	</div>
		</form>
		<div class="respo-group">
			<div id="line-grp-template" data-role="exemple" class="col-xs-12" style="display: none;">
				<div class="col-xs-12 col-sm-3"><em>Groupe :</em> <strong class="grp"><a></a></strong></div>
				<div class="col-xs-12 col-sm-3"><em><a href="#" data-info="missing">Absences :</a></em> <strong class="abs">12</strong></div>
				<div class="col-xs-12 col-sm-3"><em><a href="#" data-info="delivery">Livrables :</a></em> <strong class="del">2</strong></div>
				<div class="col-xs-12 col-sm-3"><span class="fa fa-book"></span><em class="mk"></em></div>
			</div>
		</div>
		<div class="col-xs-12" style="padding-left:0px;" data-role="more-groups">
			<a href="Promo" style="dislplay:block"><em>Voir plus de group...</em></a>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1 numbers">
		<h4>- Les notes -</h4>
		<div class="col-xs-6 col-xs-offset-3 app-empty" style="display:none;" >
			<img src="img/empty/nomark.svg" alt="" class="app-empty-img"/>
		</div>
		<div class="col-xs-12 col-xs-offset-0 chart" style="display:none;">
			<div class="col-xs-5 col-sm-2 col-sm-offset-1 barchart-promo">
				<label>Moyenne des classes</label>
				<canvas id="barchart-canvas" width="100" height="100"></canvas>
			</div>
			<div class="col-xs-12 col-sm-6 col-sm-offset-1 barchart-prom-legend" style="margin-top:9%;">
				<div class="col-xs-6 ">
					<span class="badge" data-info="47">0%</span>
					<em>d'étudiants notés.<br/> </em>
				</div>
				<div class="col-xs-6">
					Notes par groupe: <strong id="class"></strong><br/>
					<ul>
						<li>Cliquez sur le graphique pour obtenir les notes par groupe.</li>	
					</ul>
					<div class="fa-container" style="display: none; margin:auto; width:56px; margin-top:5%;">
						<span class="fa fa-spinner fa-pulse fa-4x" ></span>
					</div>	
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1 missings">
		<h4>- Les absences -</h4>
		<div class="col-xs-6 col-xs-offset-3 app-empty" style="display:none;" >
			<img src="img/empty/missing.svg" alt="" class="app-empty-img"/>
		</div>
		<div class="col-xs-12 col-xs-offset-0 chart" > <!-- style="display:none;" -->
			<canvas id="linechart-canvas" width="100" height="20"></canvas>
		</div>
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
					<label>Ajouter un sujet d'APP pour ce semestre</label>
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
					<label>Ajouter une liste d'étudiants </label> <a href="#" id="csv-reminder"> Revoir le format accepté</a>
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
					<label>Importer une sauvegarde </label> <a href="#" id="backup-reminder"> Revoir le contenu d'une sauvegarde</a>
					<div class="progress test" style="display: none">
						<div class="progress-bar progress-bar-striped active"
							 role="progressbar" style="width: 0%">
						</div>
					</div>
					<input type="file" name="backup_file" id="input_backup" class="input-class"  disabled="true" data-show-preview="false" />
				</div>
			</form>
		</div>
		<div class="col-xs-12">
			<div class="btn btn-default disabled">
				Réaliser une sauvegarde
			</div>
			<div class="btn btn-danger disabled">
				Archiver le semestre
			</div>
		</div>
	</div>
</div>