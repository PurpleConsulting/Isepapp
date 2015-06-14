<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
		Liste des tuteurs
		<small> - <span class="fa fa-trash-o master"></span> - </small>
	</h1>
</div>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 add-tutor">
		<a href="#" class="form-show">Ajouter un tuteur <span class="fa fa-plus"></span></a>
		<form action="" method="post" class="form-horizontal no-enter" style="display: none;">
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" name="new_first_name" placeholder="Prénom du tuteur" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" name="new_last_name" placeholder="Nom du tuteur" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" name="new_pseudo" placeholder="Pseudo du tuteur: ldivad" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="email" name="new_email" placeholder="Email tuteur" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 as-select">
					<input type="password" name="new_password" placeholder="Mot de passe" class="form-control" disabled/>
				</div>
				<div class="col-sm-6" style="padding-top:6px;">
					<input class="toggle-input" type="checkbox" name="is_isep" /> Tuteur externe ISEP
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
					<select class="selectpicker" name="new_class">
							<option value="null">Sélectionnez une classe</option>
						<c:forEach var="_class" items="${allClass}" varStatus="status">
							<option value="${_class}"><c:out value="${_class}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group welcome-box">
				<div class="col-sm-4">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
				<button type="submit" class="btn btn-default"> Ajouter </button>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 list-tutors">
	<c:forEach var="tutor" items="${tutors}" varStatus="status">
		<div class="alert alert-tutor">
			<div class="fa-container">
			<span class="fa ${tutor.getPosition() == 'respo' ? 'fa-trash standalone' : 'fa-trash-o slave'}" data-target="${tutor.getPseudo()}"></span>
			<span class="fa fa-pencil" data-target="${tutor.getPseudo()}"></span>
			</div>
			<ul class="outer-li">
				<li data-naming="pseudo" style="display: none;"><c:out value="${tutor.getPseudo()}"></c:out></li>
				<li data-naming="last_name">Nom: <em><c:out value="${tutor.getFirstName()}"></c:out></em></li>
				<li data-naming="first_name">Prenom: <em><c:out value="${tutor.getLastName()}"></c:out></em></li>
				<li data-naming="email">Email: <em><c:out value="${tutor.getMail()}"></c:out></em></li>
				<li data-naming="groups">Groupes: <ul class="inner-li">
					<c:forEach var="g" items="${groups.get(tutor.getPseudo())}" varStatus="status">
						<li><em>
							<a href="Groups?scope=${g}">
								<c:out value="${g}"></c:out><c:if test="${!status.last}">,</c:if>
							</a>
						</em></li>
					</c:forEach>
				</ul></li>
			</ul>
		</div>
	</c:forEach>
	</div>
</div>