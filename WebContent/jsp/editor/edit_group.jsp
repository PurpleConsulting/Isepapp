<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
		Modification du Groupe
		<small> - <c:out value="${group.getName()}"></c:out> - <span class="fa fa-trash-o fa-2x"></span></small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 status">
		<h4>- Propiétés du groupe -</h4>
		<form class="form-inline" method="post" action="AlterGroups">
			<div class="line">
				<div class="form-group">
					<input type="hidden" name="scope" value="${group.getName()}"></input>
					<input name="new_name" type="text"  value="${group.getName()}" class="form-control name" placeholder="Groupe: G**"/>
				</div>
				<div class="form-group">
					<select name="new_tutor" class="form-control selectpicker" data-header="Selectionnez un Tuteur">
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
			<li class="list-group-item active"><span class="fa fa-user-plus fa-2x"></span> Ajouter un étidiant au group.</li>
			<c:forEach var="student" items="${group.getMembers()}" varStatus="status">
				<li class="list-group-item std">
					<span class="fa fa-user-times fa-2x"></span> 
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