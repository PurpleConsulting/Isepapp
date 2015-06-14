<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Evaluation individuelle
	<small> - <c:out value="${sessionScope.user.getPseudo()}"></c:out> </small>
</h1>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
		Nom : <c:out value="${student.getLastName()}"></c:out><br />
		Prénom : <c:out value="${student.getFirstName()}"></c:out><br />
		Groupe : <c:out value="${student.getGroup()}"></c:out><br />
	</div>
</div>
<div class="row">
		<div class="col-xs-offset-1 col-xs-10 grid">
		<div role="tabpanel">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
				<c:forEach var="skill" items="${skills}" varStatus="status">
					<c:if test="${skill.getId() != 0}">
					<li role="presentation" class='${status.index == 1 ? "active":""}'>
						<a href="#tab${status.index}" role="tab" data-toggle="tab">
					  		<c:out value="${status.index}. ${skill.getTitle()}"></c:out>
						</a>
					</li>
					</c:if>
				</c:forEach>
			</ul>
		  	<div class="tab-content">
		  		<c:forEach var="skill" items="${skills}" varStatus="status">
		  		<c:if test="${skill.getId() != 0 }">
		  			<div role="tabpanel" class='tab-pane ${status.index == 1 ? "active":""}' id="tab${status.index}">
		  				<form id="form${skill.getId()}" class="groupgrid">
			    			<h4 data-naming="${skill.getTitle()}"><c:out value='${status.index} - ${skill.getSubtitle()}'></c:out></h4>
			    			<c:forEach var="sub_skill" items="${skill.getSubSkills()}" varStatus="status">
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
						</form>
					</div>
				</c:if>
				</c:forEach>
		 	 </div>
		</div>
	</div>
</div>

