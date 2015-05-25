<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div>

	<h1>
		Evaluation croisée :
		<c:out value="${group.getName()}"></c:out>
	</h1>

	<div class="deadline">Deadline à (heure de depot)</div>


	<div id="name_group"></div>

	<!-- Nav tabs -->
	<div role="tabpanel">
		<ul class="nav nav-tabs" role="tablist" id="tabs">
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<li role="presentation" class='${status.count==1 ? "active":""}'>
					<a href="#tab${status.count}" role="tab" data-toggle="tab"> <c:out
							value="${status.count}. ${skill.getTitle()}"></c:out>
				</a>
				</li>
			</c:forEach>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<form action="Controls" method="post" id="form${skill.getId()}">
					<div role="tabpanel"
						class='tab-pane ${status.count==1 ? "active":""}'
						id="tab${status.count}">
						<h2>
							<c:out value='${status.count} - ${skill.getSubtitle()}'></c:out>
						</h2>
						<c:forEach var="sub_skill" items="${skill.getSub_skills()}"
							varStatus="status">

							<hr />

							<h3>
								<c:out value='${sub_skill.getTitle()} : '></c:out>
							</h3>
							<div class="radio" class="line">
								<c:forEach var="value" items="${values}" varStatus="status">
									<label> <input type="radio" name='${sub_skill.getId()}'
										id="value${status.count}" value='${value.getId()}'
										${status.count==1 ? "checked":""}> <c:out
											value='${value.getTitle()}'></c:out>
									</label>

									<input type="hidden" value='${sub_skill.getId()}' class="val">

								</c:forEach>
								<c:set var="val" value="${skill.getId()}" />
							</div>
						</c:forEach>
					</div>
					<button class="btn btn-default btn-test" value="${val}">Bouton</button>
				</form>
			</c:forEach>
		</div>
	</div>

	<!-- 	<div class="nom"> -->

	<!-- 		<button type="button" onclick="myFunction()" class="btn btn-default"><</button> -->
	<%-- 		<c:forEach var="student" items="${group.getMembers()}" --%>
	<%-- 			varStatus="status"> --%>
	<%-- 			<c:out value="${student.getFirstName()} ${student.getLastName()}"></c:out> --%>
	<%-- 		</c:forEach> --%>
	<!-- 		<button type="button" class="btn btn-default">></button> -->

	<!-- 	</div> -->

	<!-- </div> -->

	<!-- <div id="formulaire"> -->
	<!-- 	<form method="post" action="traitement.php"> -->

	<!-- 		<div class="couleur"></div> -->
	<!-- 		<div class="table-responsive"> -->
	<!-- 			<table class="table table-bordered"> -->
	<!-- 				<tr>compétence n°1 -->
	<!-- 				</tr> -->

	<!-- 				<tr> -->
	<!-- 					<th class="sscomp1">Sous-compétence</th> -->
	<!-- 					<th class="mark">Loin d'être acquis</th> -->
	<!-- 					<th class="mark">En cours d'acquisition</th> -->
	<!-- 					<th class="mark">Acquis</th> -->
	<!-- 					<th class="mark">Au-delà</th> -->
	<!-- 				</tr> -->

	<!-- 				<tr> -->
	<!-- 					<td>Sous-compétence n°1</td> -->
	<!-- 					<td class="sscomp1"><label> <input type="radio" -->
	<!-- 							name="sscomp1" id="optionsRadios2" value="Loin d'être acquis"> -->
	<!-- 					</label></td> -->

	<!-- 					<td class="sscomp1"><label> <input type="radio" -->
	<!-- 							name="sscomp1" id="optionsRadios2" value="En cours d'acquisition"> -->
	<!-- 					</label></td> -->

	<!-- 					<td class="sscomp1"><label> <input type="radio" -->
	<!-- 							name="sscomp1" id="optionsRadios2" value="Acquis"> -->
	<!-- 					</label></td> -->

	<!-- 					<td class="sscomp1"><label> <input type="radio" -->
	<!-- 							name="sscomp1" id="optionsRadios2" value="Au-delà"> -->
	<!-- 					</label></td> -->
	<!-- 				</tr> -->
	<!-- 			</table> -->
	<!-- 		</div> -->
	<!-- 		<button type="submit" class="btn btn-default">envoyer</button> -->
	<!-- 	</form> -->
</div>