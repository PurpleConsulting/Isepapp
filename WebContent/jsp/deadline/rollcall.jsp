<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-10 col-xs-offset-1">
		Gestion des absences <small>- 
		<c:out value="${user.getPseudo()}"></c:out> 
		<span class="fa fa-clock-o master-block"></span></small>
	</h1>
</div>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row">
	<div class="col-xs-10">
		
	</div>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<div class="alert alert-info alert-dismissible" role="alert">
	  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  		<strong>Bienvenu,</strong> sur la page d'appel
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<c:if test="${empty groups}">
			<div class="col-xs-8 col-xs-offset-2">
				<img src="img/empty/group.svg" alt=""  class="app-empty-img"/>
			</div>
		</c:if>
		<c:if test="${!empty groups}">
		<form class="" action="" method="post">
			<c:forEach var="grp" items="${groups}" varStatus="status">
				<div class="panel panel-primary">
				<div class="panel-heading">
					<h4> <span class="fa fa-clock-o bold-clock"></span> Groupe <c:out value="${grp.getName()}"></c:out> </h4>
				</div>
				<div class="panel-body" style="display:none;">
					<c:forEach var="student" items="${grp.getMembers()}" varStatus="status">
						<div class="form-group" >
							<div class="row">
								<div class="col-xs-4">
									<label class="student"> <input type="checkbox"  name="" value="" class="check_missing"/>
										<c:out value="${ student.getFirstName()}"></c:out>
										<c:out value="${ student.getLastName()}"></c:out>
									</label>
								</div>
								<div class="col-xs-offset-1 col-xs-3">
									<button disabled href="#" class="btn btn-default late">Retard ?</button>
									<input disabled type="time" id="new_time" name="" value="" placeholder="10:00" class="form-control" style="display:none;"/>
								</div>
							</div>
							<input disabled type="text" name="" value="" class="form-control supporting"
							placeholder="Motif de l'absence ou du retard."/>
						</div>
					</c:forEach>
				</div> 
			</div>
			</c:forEach>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">
					Confirmer les absences
				</button>
			</div> 
		</form>
		</c:if>
	</div>
</div>
