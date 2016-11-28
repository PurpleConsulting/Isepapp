<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row no-row">
	<h1 class="col-xs-offset-1 col-xs-10">Evaluation individuelle
		<small> - <c:out value="${sessionScope.user.getPseudo()}"></c:out> </small>
	</h1>
</div>
<div class="col-xs-12 no-col">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row no-row">
	<div class="alert alert-student col-xs-offset-1 col-xs-10">
		<div class="col-sm-6">
			<em class="std-label">Nom :</em> <c:out value="${student.getLastName()}"></c:out><br />
			<em class="std-label">Prénom :</em> <c:out value="${student.getFirstName()}"></c:out><br />
			<em class="std-label">Groupe :</em> <c:out value="${student.getGroup()}"></c:out><br />
		</div>
		<div class="col-xs-6 class legend">
			<label class="checked-grp"><input type="radio" checked/>&nbsp; Note de groupe</label><br/>
			<label class="checked"><input type="radio" checked/>&nbsp; Note personnelle</label><br/>
		</div>
	</div>
</div>
<div class="row">
		<div class="col-xs-offset-1 col-xs-10 grid" data-target="${student.getPseudo()}">
		<div role="tabpanel">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
				<c:forEach var="skill" items="${skills}" varStatus="status">
					<c:if test="${skill.getId() != 0}">
					<li role="presentation" class='${status.count == 1 ? "active":""}'>
						<a href="#tab${status.count}" role="tab" data-toggle="tab">
					  		<c:out value="${status.count}. ${skill.getTitle()}"></c:out>
						</a>
					</li>
					</c:if>
				</c:forEach>
			</ul>
		  	<div class="tab-content">
		  		<c:forEach var="skill" items="${skills}" varStatus="status">
		  		<c:if test="${skill.getId() != 0 }">
		  			<div role="tabpanel" class='tab-pane ${status.count == 1 ? "active":""}' id="tab${status.count}">
		  				<form id="form${skill.getId()}" class="groupgrid" action='PersoControls?pseudo=${student.getPseudo()}' method="post">
		  					<input type="hidden" name="student" value="${student.getPseudo()}" />
			    			<h4 data-naming="${skill.getTitle()}"><c:out value='${status.count} - ${skill.getSubtitle()}'></c:out></h4>
			    			<c:forEach var="sub_skill" items="${skill.getSubSkills()}" varStatus="status">
					  			<hr/><h5><c:out value="${sub_skill.getTitle()}"></c:out></h5>
						  		<div class="line">
						  			<c:forEach var="value" items="${values}" varStatus="status">
								  		<label>
								    	<input type="radio" name="sub_skill_${sub_skill.getId()}" value="${value.getId()}">
								    	<c:out value='${value.getTitle()}'></c:out>
								  		</label><br/>
									</c:forEach>
									<c:set var="val" value="${skill.getId()}"/>
								</div>
					  		</c:forEach>
					  		<button class="btn btn-default marker" type="submit"><span class="fa fa-crosshairs"></span>  Noter</button><!-- fa-cog fa-spin -->
						</form>
					</div>
				</c:if>
				</c:forEach>
		 	 </div>
		</div>
	</div>
</div>

