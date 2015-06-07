<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">
		Evaluation croisée : <small><c:out value="${group.getName()}"></c:out></small>
	</h1>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
		<div class="alert alert-info">
		L'évaluation de votre groupe est à compléter avant le <c:out value="${deadline.printDateLimit()}"></c:out>. 
		Au-delà cette date le groupe ne pourra plus compéter l'évaluation croisée.
		</div>
		<!-- nom des membres du  groupe -->
		<div class="eleve_groupe">
			<button type="button" class="btn btn-default prev" disabled>
				<span class="fa fa-arrow-left"></span>
			</button>
			<ul class="group-ul">
				<c:forEach var="student" items="${group.getMembers()}" varStatus="status">
					<li class="${status.count == 1 ? 'active' : ''}">
						<c:out value="${student.getFirstName()} ${student.getLastName()}"></c:out>
					</li>
				</c:forEach>
			</ul>
			<button type="button" class="btn btn-default nxt">
				<span class="fa fa-arrow-right"></span>
			</button>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 marker">
		<div role="tabpanel">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
				<li role="presentation" class='active'>
					<a href="#tab0" role="tab" data-toggle="tab">
						<c:out value="${skills.getTitle()}"></c:out>
					</a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<c:forEach var="student" items="${group.getMembers()}" varStatus="status">	
					<form action="CrossControls" method="post" class="cross-form${status.count == 1 ? ' active' : ''}">
						<h3 style = "color: red"><c:out
								value="${student.getFirstName()} ${student.getLastName()}"></c:out></h3>
						<c:forEach var="sub_skill" items="${skills.getSubSkills()}"
							varStatus="status">
							<div role="tabpanel" class='tab-pane ${status.count==1 ? "active":""}' id="tab${status.count}">
								<h4>
									<c:out value='${status.count} - ${sub_skill.getTitle()}'></c:out>
								</h4>
								
								<div class="radio" class="line">
									<c:forEach var="value" items="${values}" varStatus="status">
										<label>
											<input type="radio" name="${sub_skill.getId()}" id="value${status.count}" value="${value.getId()}" ${status.count==1 ? "checked":""}> 
											<c:out value="${value.getTitle()}"></c:out>
										</label>
										<input type="hidden" value='${subSkill.getId()}'>
									</c:forEach>
									<c:set var="val" value="${skill.getId()}" />
								</div>
							</div>
							<hr />
						</c:forEach>
<%-- 						<p> <label><c:out value='${student.getPseudo()}'></c:out></label> </p> --%>
						<button class="btn btn-default btn-test" value="${val}">Bouton</button>
						<input type="hidden" value='${student.getPseudo()}' name = 'student'>
					</form>
					
				</c:forEach>
			
			</div>
		</div>
	</div>
</div>