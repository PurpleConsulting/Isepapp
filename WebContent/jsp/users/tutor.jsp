<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
		Liste des tuteurs
		<small> - <span class="fa fa-trash-o"></span> - </small>
	</h1>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 add-tutor">
		<div><a href="#" class="form-show">Ajouter une tuteur <span class="fa fa-plus"></span></a></div>
		<form action="#" method="post" class="form-horizontal" style="display: none;">
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" name="new_first_mane" placeholder="Prénom du tuteur" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" name="new_last_name" placeholder="Nom du tuteur" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="email" name="new_email" placeholder="Email tuteur" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 as-select">
					<input type="password" name="" placeholder="Mot de passe" class="form-control" disabled/>
				</div>
				<div class="col-sm-6" style="padding-top:6px;">
					<input class="toggle-input" type="checkbox" name="is_isep" /> Tuteur externe ISEP
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<select class="selectpicker">
						<option>G1</option>
						<option>G2</option>
						<option>G3</option>
					</select>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 list-tutors">
		<div class="alert alert-info">
			<strong>tuteur1</strong>
		</div>
		<div class="alert alert-info">
			<strong>tuteur1</strong>
		</div>
		<div class="alert alert-info">
			<strong>tuteur1</strong>
		</div>
		<div class="alert alert-info">
			<strong>tuteur1</strong>
		</div>
		<div class="alert alert-info">
			<strong>tuteur1</strong>
		</div>
	</div>
</div>