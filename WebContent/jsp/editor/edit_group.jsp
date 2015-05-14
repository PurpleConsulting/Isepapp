<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Modification du Groupe
	<small> - G0A - <span class="fa fa-trash-o fa-2x"></span></small>
</h1>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 status">
		<h4>- Propiété du groupe -</h4>
		<form class="form-inline" method="post" action="">
			<div class="line">
				<div class="form-group">
					<input type="text" class="form-control name" placeholder="Groupe: G**"/>
					<select class="form-group selectpicker">
					    <option>mmenceny1</option>
					    <option>mmenceny2</option>
					    <option>mmenceny3</option>
					  </select>
					<input type="submit" class="form-control"/>
				</div>
			</div>
		</form>
	</div>
</div>
<hr class="col-xs-offset-1 col-xs-10" />
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 add-remove">
		<h4>- Memebres du groupe -</h4>
		<ul class="list-group col-md-7">
			<li class="list-group-item active">Ajouter un étidiant au group. <span class="fa fa-user-plus fa-2x"></span></li>
			<li class="list-group-item std">Loic DIVAD - <a href="Students?pseudo=#"><em>ldivad</em></a> <span class="fa fa-user-times fa-2x"></span></li>
			<li class="list-group-item std">Loic DIVAD - <a href="Students?pseudo=#"><em>ldivad</em></a><span class="fa fa-user-times fa-2x"></span></li>
			<li class="list-group-item std">Loic DIVAD - <a href="Students?pseudo=#"><em>ldivad</em></a><span class="fa fa-user-times fa-2x"></span></li>
			<li class="list-group-item std">Loic DIVAD - <a href="Students?pseudo=#"><em>ldivad</em></a><span class="fa fa-user-times fa-2x"></span></li>
			<li class="list-group-item std">Loic DIVAD - <a href="Students?pseudo=#"><em>ldivad</em></a><span class="fa fa-user-times fa-2x"></span></li>
		</ul>
	</div>
</div>
<hr class="col-xs-offset-1 col-xs-10"/>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 delivery">
		<h4>- Les livrables groupe -</h4>
		<ul class="list-group col-md-7">
			<li class="list-group-item std"></li>
			<li class="list-group-item std"></li>
			<li class="list-group-item std"></li>
		</ul>
	</div>
</div>
<hr class="col-xs-offset-1 col-xs-10"/>