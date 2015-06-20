<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
		Modification du Groupe
		<small> 
			- <a class="chameleon" href="Groups?scope=${group.getName()}"><c:out value="${group.getName()}"></c:out></a> - 
			<span data-delete="${group.getName()}" class="fa fa-trash-o link-dialog-grp"></span></small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 status">
		<h4>- Propiétés du groupe - <small>Responsable d'app uniquement</small></h4>
		<form class="form-inline" method="post" action="AlterGroups">
			<div class="line">
				<div class="form-group">
					<input type="hidden" name="scope" value="${group.getName()}"></input>
					<input name="new_name" type="text"  value="${group.getName()}" class="form-control name" placeholder="Groupe: G**"/>
				</div>
				<div class="form-group">
					<select name="new_tutor" class="form-control selectpicker" data-header="Selectionnez un Tuteur">
						<c:if test="${group.getTutor() == '(Vide)'}">
							<option selected>Aucun Tuteur</option>
						</c:if>
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
			<li class="list-group-item active">
				<a class="chameleon" data-toggle="modal" data-target="#modaladd"><span data-toggle="modal" data-target="#myModal" class="fa fa-user-plus fa-2x"></span></a>
				 Ajouter un étidiant au group.
			</li>
			<c:forEach var="student" items="${group.getMembers()}" varStatus="status">
				<li class="list-group-item std">
					<a class="chameleon link-dialog-std" href="#" data-delete="${student.getPseudo()}"><span class="fa fa-user-times fa-2x"></span></a> 
					<c:out value="${student.getFirstName()}"></c:out>
					<c:out value="${student.getLastName()}"></c:out> - 
					<a href="Students?pseudo=${student.getPseudo()}">
						<em><c:out value="${student.getPseudo()}"></c:out></em>
					</a> 
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<hr class="col-xs-offset-1 col-xs-10"/>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 delivery">
		<h4>- Livrables dugroupe -</h4>
		<ul class="list-group col-md-7">
			<li class="list-group-item std">
				<span class="fa fa-times-circle"> </span>
				<button type="button" class="btn btn-default">Livrable20150312.pdf</button>
			</li>
			<li class="list-group-item std">
				<span class="fa fa-times-circle"> </span>
				<button type="button" class="btn btn-default">Livrable20150312.zip</button>
			</li>
		</ul>
	</div>
</div>
<hr class="col-xs-offset-1 col-xs-10"/>



<!-- INVISIBLE -->
<div id="modaladd" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Ajouter un étudiant au group <c:out value="${group.getName()}"></c:out></h4>
			</div>
			<div class="modal-body">
				<form id="adduser" class="form-horizontal" method="post" action="AlterGroups?scope=${group.getName()}">
					<div class="form-group">
						<label for="std_first_name" class="col-sm-2 control-label">Prénom</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="std_first_name"
								placeholder="Prénom">
						</div>
					</div>
					<div class="form-group">
						<label for="std_name" class="col-sm-2 control-label">Nom</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="std_last_name"
								placeholder="Nom">
						</div>
					</div>
					<div class="form-group">
						<label for="std_pseudo" class="col-sm-2 control-label">Pseudo</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="std_pseudo"
								placeholder="Pseudo ISEP, Julie LUTZ: jlutz">
						</div>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="std_no" placeholder="n° Isep">
						</div>
						<div class="col-sm-2">
							<input type="text" class="form-control" disabled value="${group.getName()}">
						</div>
					</div>
					<div class="form-group">
						<label for="std_email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="std_email"
								placeholder="Email">
						</div>
					</div>
					<!--<button style="Display:none;" type="submit" class="btn btn-primary no-btn" id="addin"></button>-->
				</form>
			</div>
			<div class="modal-footer">
				<span style="display: none;"><input type="checkbox" /></span>
				<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
				<button onclick="$('form#adduser').submit();" type="submit" class="btn btn-primary" id="adduser">Ajouter</button>
			</div>
		</div>
	</div>
</div>