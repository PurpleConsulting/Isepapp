<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<div class="row">
		<h1 class="col-xs-offset-1 col-xs-10">Les Deadlines</h1>
	</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>

<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
		<em style="color:#337AB7">Ajouter un deadline</em>
		<form class="form-inline group" method="post" action="Deadlines">
			<div class="form-group deadline">
			    <div class="input-group">
			      <input type="text" name="new_desc" class="form-control" id="new_desc" placeholder="Description">
			      <input type="hidden" value="${usession.getId()}" name="tuteur">
			    </div>
		  	</div>
		  	<div class="form-group deadline">
			    <div class="input-group">
			      <input type="date" name="new_date" class="form-control" id="new_date" placeholder="Date limite">
			    </div>
		  	</div>
		  	<div class="form-group deadline">
			    <div class="input-group">
			      <input type="time" name="new_time" class="form-control" id="new_time" placeholder="Time limite">
			    </div>
		  	</div>
		  	 <div class="form-group deadline">
			  <select name="new_grp" class="form-control selectpicker" title="(Vide)" data-header="Selectionnez un Groupe">
				<c:forEach var="group" items="${groups}" varStatus="status">
						<option value="${group}">
							<c:out value="${group}"></c:out>
						</option>
				</c:forEach>
			  </select>
			</div>
			<div class="form-group">
		  		<button type="submit" class="btn btn-default">Ajouter </button>
		  	</div>
		</form>
	
		
		<em style="color:#337AB7">Les deadlines</em>
			<div class="latest-group list">
			<input type="hidden" value="${fn:length(deadline)-1}" id="taille"/>
			<form class="form-inline off deadlineDate" method="post" action="Deadlines">
				<div class="form-group button">
					<a class="btn btn-default modify" href="#" role="button"><span class="fa fa-pencil"></span> <span>Modifier</span></a>
					<button type="submit" class="btn btn-default" id="valid"><span class="fa fa-check"> </span> Valider</button>
				</div>
				<input type="hidden" value="${fn:length(deadline)-1}" name="number"/>
				<c:forEach var="i" begin="0" end='${fn:length(deadline)-1}'>
				<div class="alert alert-info design">
					<input type="hidden" value="${deadline[i].getStatus()}" id="status${i}"/>
					<input type="hidden" value="${deadline[i].getId()}" name="id${i}"/>
						<div class="form-group deadline">
						Groupe: <strong><c:out value="${deadline[i].getGroup()}"></c:out> </strong> 
						</div>
						<div class="form-group deadline">
						Titre:  <strong><c:out value="${deadline[i].getDescription()}"></c:out> </strong> 
						</div>
						<div class="form-group deadline">
							<c:forEach var="j" begin="0" end='${fn:length(user)-1}'>
							 	<c:if test="${user[j].getGroup() == deadline[i].getGroup()}" > 
						Tuteur: <strong><c:out value="${user[j].getFirstName()}"></c:out> <c:out value="${user[j].getLastName()}"></c:out> </strong>
								</c:if>
							</c:forEach> 
						</div> 
						<div class="form-group deadline">
							Date:<input type="date" class="form-control dateDisabled"  name="datelim${i}" id="datelim${i}" value="${deadline[i].getDateLimit().toString('yyyy-MM-dd')}"/>
						</div>
						<div class="form-group">
							Heure:<input type="time" class="form-control dateDisabled"  name="timelim${i}"  id="timelim${i}"value="${deadline[i].getDateLimit().toString('HH:mm:ss')}"/>
						</div>
						<div class="form-group">
							<a class="remove" id="del${i}" href="#"><span class="fa fa-trash-o fa-lg"></span></a> 
						</div>
						
					
				</div>
				</c:forEach>
				</form>
			</div>
	</div>
</div>


