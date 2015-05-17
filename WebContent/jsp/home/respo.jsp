<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">
		Accueil
		<small> - APP - 2016 S1</small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 group">
		<h4>- Derniers groups consultés -</h4>
		<div class="latest-group">
			<div class="alert alert-info"> Groupe G4C - Nombre de note: 0  - Absences: 0  - les livrables(0) - tuteur: ldivad</div>
			<div class="alert alert-info"> Groupe G4C - Nombre de note: 0  - Absences: 0  - les livrables(0) - tuteur: ldivad</div>
			<div class="alert alert-info"> Groupe G4C - Nombre de note: 0  - Absences: 0  - les livrables(0) - tuteur: ldivad</div>
			<a href="#"><em>Voir plus de group...</em></a>
		</div>
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
		  		<button type="submit" class="btn btn-default"><span class="fa fa-plus"> </span> Ajouter un Group</button>
		  	</div>
		  	<div class="form-group">
		  		<a class="btn btn-default" href="#" role="button"><span class="fa fa-plus"> </span>  Ajouter un Tuteur</a>
		  	</div>
		</form>
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
	</div>
</div>