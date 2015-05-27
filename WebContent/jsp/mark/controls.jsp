<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Evaluation des groupes
	<small> - <c:out value="${sessionScope.user.getPseudo()}"></c:out> </small>
</h1>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 select-group">
		<form action="Controls" method="post">
			<div class="form-group">
				Recherche par groupe d'APP :
				<select class="selectpicker select-group" id="group">
					<option value="init">Sélectionnez un groupe</option>
					<c:forEach var="group" items="${group_names}" varStatus="status">
						<option value="${group}"><c:out value="${group}"></c:out></option>
					</c:forEach>			
				</select>
			</div>
		</form>
		<div class="alert alert-info" id="name_group" style="display:none;"></div>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 grid">
		<div role="tabpanel">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
				<c:forEach var="skill" items="${skills}" varStatus="status">
					<li role="presentation" class='${status.count==1 ? "active":""}'>
						<a href="#tab${status.count}" role="tab" data-toggle="tab">
					  		<c:out value="${status.count}. ${skill.getTitle()}"></c:out>
						</a>
					</li>
				</c:forEach>
			</ul>
		  	<div class="tab-content">
		  		<c:forEach var="skill" items="${skills}" varStatus="status">
		  			<div role="tabpanel" class='tab-pane ${status.count==1 ? "active":""}' id="tab${status.count}">
		  				<form id="form${skill.getId()}" class="groupgrid">
			    			<h4 data-naming="${skill.getTitle()}"><c:out value='${status.count} - ${skill.getSubtitle()}'></c:out></h4>
			    			<c:forEach var="sub_skill" items="${skill.getSub_skills()}" varStatus="status">
					  			<hr/><h5><c:out value="${sub_skill.getTitle()}"></c:out></h5>
						  		<div class="line">
						  			<c:forEach var="value" items="${values}" varStatus="status">
								  		<label>
								    	<input type="radio" name="${sub_skill.getId()}" value="${value.getId()}">
								    	<c:out value='${value.getTitle()}'></c:out>
								  		</label>
									</c:forEach>
									<c:set var="val" value="${skill.getId()}"/>
								</div>
					  		</c:forEach>
					  		<button class="btn btn-default marker" disabled><span class="fa fa-crosshairs"></span>  Noter</button><!-- fa-cog fa-spin -->
					  		<button class="btn btn-default adder" disabled><span class="fa fa-plus"></span> Ajouter une note personnelle</button>
						</form>
					</div>
				</c:forEach>
		 	 </div>
		</div>
	</div>
	<div class="conf_box col-xs-offset-1 col-xs-10 alert" id="confirmation_box" style="display:none;" role="alert">
		<button type="button" class="close"><span aria-hidden="true">&times;</span></button>
	</div>
</div>

