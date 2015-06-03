<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10"> 
		Evaluation croisée :
		<small><c:out value="${group.getName()}"></c:out></small>
	</h1>
</div>
<c:out value="${deadline.printDateLimit()}"></c:out>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
		<div class="alert alert-info">Deadline à (heure de depot)</div>
		<!-- nom des membres du  groupe -->
		<div class="eleve_groupe">
			<button type="button" onclick="myFunction()" class="btn btn-default">
				&larr; précédent</button>
			<c:forEach var="student" items="${group.getMembers()}"
				varStatus="status">
				<c:out value="${student.getFirstName()} ${student.getLastName()}"></c:out>
			</c:forEach>
			<button type="button" class="btn btn-default">suivant &rarr;</button>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
	<!-- Compétence et sous-compétence -->
	<!-- Nav tabs -->
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
			<form action="Controls" method="post" id="form">
				<c:forEach var="sub_skill" items="${skills.getSubSkills()}"
					varStatus="status">
					<div role="tabpanel"
						class='tab-pane ${status.count==1 ? "active":""}'
						id="tab${status.count}">
						<h4>
							<c:out value='${status.count} - ${sub_skill.getTitle()}'></c:out>
						</h4>
						<div class="radio" class="line">
							<c:forEach var="value" items="${values}" varStatus="status">
								<label> <input type="radio" name='${subSkill.getId()}'
									id="value${status.count}" value='${value.getId()}'
									${status.count==1 ? "checked":""}> <c:out
										value='${value.getTitle()}'></c:out>
								</label>

								<input type="hidden" value='${subSkill.getId()}' class="val">

							</c:forEach>
							<c:set var="val" value="${skill.getId()}" />
						</div>
					</div>
					<hr/>
				</c:forEach>
				<button class="btn btn-default btn-test" value="${val}">Bouton</button>
			</form>
		</div>
	</div>
	</div>
</div>