<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">Les deadlines</h1>
</div>
<div class="col-xs-12">
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 insert">
		<em style="color:#337AB7">Ajouter une deadline</em>
		<form class="form-inline group" method="post" action="Deadlines">
			<div class="form-group deadline">
			    <div class="input-group">
			      <input type="text" name="new_desc" class="form-control" id="new_desc" placeholder="Description">
			      <input type="hidden" value="${usession.getId()}" name="tuteur">
			    </div>
		  	</div>
		  	<div class="form-group deadline">
			    <div class="input-group">
			      <input type="text" name="new_date" class="form-control time" id="new_date" placeholder="2015-06-20">
			    </div>
		  	</div>
		  	<div class="form-group deadline">
			    <div class="input-group">
			      <input type="text" name="new_time" class="form-control time" id="new_time" placeholder="23:00">
			    </div>
		  	</div>
		  	 <div class="form-group deadline select">
			  <select name="new_grp" class="">
				<c:forEach var="group" items="${groups}" varStatus="status">
						<option value="${group}"><c:out value="${group}"></c:out></option>
				</c:forEach>
			  </select>
			</div>
			<div class="checkbox">
			    <label>
			      <input type="checkbox" name="checkCross"> Evaluation croisée
			    </label>
			 </div>
			<div class="form-group deadline">
		  		<button type="submit" class="btn btn-default"><span class="fa fa-plus"></span> Ajouter </button>
		  	</div>
		</form>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 display">
		<em style="color:#337AB7">Les deadlines en cours</em>
			<div class="latest-group list">
			<input type="hidden" value="${fn:length(deadline)}" id="taille"/>
			<form class="form-inline off deadlineDate" method="post" action="Deadlines">
				<c:if test="${!empty deadline}">
					<div class="form-group button">
						<a class="btn btn-default modify" href="#" role="button"><span class="fa fa-pencil"></span> <span>Modifier</span></a>
						<button type="submit" class="btn btn-default" id="valid"><span class="fa fa-check"> </span> Valider</button>
					</div>
				</c:if>
				<input type="hidden" value="${fn:length(deadline)}" name="number"/>
				<c:set var="length"  value="${fn:length(deadline)}"/>
				<c:if test="${empty deadline}">
					<div class="col-xs-8 col-xs-offset-2">
						<img src="img/empty/deadline.svg" alt=""  class="app-empty-img"/>
					</div>
				</c:if>
				<c:forEach var="deadline" items="${deadline}" varStatus="status">
					<c:if test="${deadline.getStatus()==true}">
						<div class="alert alert-deadline design">
							<input type="hidden" value="${deadline.getStatus()}" id="status${status.count}"/>
							<input type="hidden" value="${deadline.getId()}" name="id${status.count}"/>
								<div class="form-group col-xs-12 deadline" style="padding-left: 0px;">
								Titre:  <strong><em><c:out value="${deadline.getDescription()}"></c:out></em></strong>
								</div>
								<div class="form-group deadline noinput">
								Groupe: <strong><c:out value="${deadline.getGroup()}"></c:out></strong>
								</div>
								<div class="form-group deadline">
									<span class="lab">Date:</span><input type="text" class="form-control dateDisabled"  name="datelim${status.count}" id="datelim${status.count}" value="${deadline.getDateLimit().toString('yyyy-MM-dd')}"/>
								</div>
								<div class="form-group deadline">
									<span class="lab">Heure:</span><input type="text" class="form-control dateDisabled"  name="timelim${status.count}"  id="timelim${status.count}"value="${deadline.getDateLimit().toString('HH:mm')}"/>
								</div>
								<div class="form-group deadline" data-group="${deadline.getGroup()}" data-subject="${deadline.getDescription()}">
									<span class="fa fa-trash-o fa-lg" data-target="${deadline.getId()}"></span>
								</div>
								<c:if test="${deadline.getCompleted()}">
									<div class="form-group deadline">
										<a href="${deadline.getDeliveryPath()}" download>
											<span class="fa fa fa-check-circle"></span>
										</a>
									</div>
								</c:if>
							</div>
							</c:if>
						</c:forEach>
				</form>
			</div>
	</div>
</div>
						<!----------------------------- Deadline passée ------------------------------->
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 display terminee">
		<em style="color:#797A7C">Les deadlines terminées</em>
			<div class="latest-group list terminee">
			<input type="hidden" value="${fn:length(deadline)}" id="taille"/>
			<form class="form-inline off ">
				<c:set var="length"  value="${fn:length(deadline)}"/>
				<c:if test="${empty deadline}">
					<div class="col-xs-8 col-xs-offset-2">
						<img src="img/empty/deadline.svg" alt="" class="app-empty-img"/>
					</div>
				</c:if>
				<c:forEach var="deadline" items="${deadline}" varStatus="status">
					<c:if test="${deadline.getStatus()==false}">
						<div class="alert alert-deadline design" style="color:#91999C">
							<input type="hidden" value="${deadline.getStatus()}" id="status${status.count}"/>
							<input type="hidden" value="${deadline.getId()}" name="id${status.count}"/>
								<div class="form-group col-xs-12 deadline" style="padding-left: 0px;">
								Titre:  <strong><em><c:out value="${deadline.getDescription()}"></c:out></em></strong>
								</div>
								<div class="form-group deadline noinput">
								Groupe: <strong><c:out value="${deadline.getGroup()}"></c:out></strong>
								</div>
								<div class="form-group deadline">
									<span class="lab">Date:</span><input type="text" class="form-control dateDisabled"  name="datelim${status.count}" id="datelim${status.count}" value="${deadline.getDateLimit().toString('yyyy-MM-dd')}"/>
								</div>
								<div class="form-group deadline">
									<span class="lab">Heure:</span><input type="text" class="form-control dateDisabled"  name="timelim${status.count}"  id="timelim${status.count}"value="${deadline.getDateLimit().toString('HH:mm')}"/>
								</div>
								<div class="form-group deadline" data-group="${deadline.getGroup()}" data-subject="${deadline.getDescription()}">
									<span class="fa fa-trash-o fa-lg" data-target="${deadline.getId()}"></span>
								</div>
								<c:if test="${!deadline.getCompleted()}">
									<div class="form-group deadline">
										<span class="fa fa-exclamation-circle"></span>
									</div>
								</c:if>
								<c:if test="${deadline.getCompleted()}">
									<div class="form-group deadline">
										<span class="fa fa fa-check-circle"></span>
									</div>
								</c:if>
							</div>
							</c:if>
						</c:forEach>
				</form>
			</div>
	</div>
</div>

