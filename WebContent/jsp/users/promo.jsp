<!--  BODY PART PROM MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="prom" class="col-md-offset-1 col-md-8">
	<h1>La promotion</h1>
	<div class="info-checkpoint">
		<div class="text">
			<h5> -- Page promo : La notice --</h5>
			Vous trouverez ici l'ensemble des groupes pour tout un semestre.<br/>
			Les informations sur le tuteur, note et absences sont disponibles sur cette page.<br/>
			Les groupes sont regroupés par classe, n'hésitez pas à utiliser l'ascenseur sur votre droite pour naviguer.
		</div>
	</div>
	<c:if test="${empty allClass}">
		<img src="img/empty/group.svg" alt="" class="app-empty-img"/>	
	</c:if>
	<c:forEach var="cls" items="${allClass}" varStatus="clItem">
		<c:set var="average" value="${avg.byTitle(cls)}"/>
		<div id="Group${cls}"  class="group">
			<h2>Classe <c:out value="${cls}"></c:out></h2>
			<div class="row class-card">
				<div class="col-xs-12 col-sm-4"> <!-- style="background-color:green;"  -->
					<div class="medal"><span class="fa fa-briefcase"></span> Tuteur: <c:out value="${ prom.get(cls).get(0).getTutor()}"></c:out></div><br/>
					<div class="medal"><span class="fa fa-graduation-cap"></span> Moyenne: 
					<span class="badge"><c:out value="${fn:substring(avg.byTitle(cls).compute(),0,4)}"></c:out></span>
					</div><br/> 
					<div class="medal"><span class="fa fa-bed"></span> Absences: </div><br/>
				</div>
				<div class="col-xs-6 col-sm-offset-1 col-sm-5" style="min-width: 250px;"> <!-- background-color:blue; -->
					<canvas id="missing-chart-${cls}"></canvas>
					<div class="ledgend">Répartition des absences</div>
					<p style="text-align:center;">
					<span class="badge" style="background-color:red; color:white;">G8A</span>
					<span class="badge" style="background-color:green; color:white;">G8B</span>
					<span class="badge" style="background-color:bleu; color:white;">G8C</span>
					<span class="badge" style="background-color:purple; color:white;">G8D</span>
					</p>
				</div>
			</div>
			<c:forEach var="group" items="${prom.get(cls)}" varStatus="grItem">
					<div id="Group${group.getName()}" class="row subgroup">
						<div class="col-sm-10">
							<h3> <a href="Groups?scope=${group.getName()}"> Groupe
								<c:out value="${group.getName()}"></c:out></a><small> 
								- Tuteur: <c:out value="${group.getTutor()}"></c:out></small></h3>
							<div class="medal">
								<span class="fa fa-graduation-cap"></span>
								Note :
								<span class="badge"><c:out value="${fn:substring(average.byTitle(group.getName()).compute(),0,4)}"></c:out></span>
							</div>
							<div class="medal">
								<span class="fa fa-bed"></span>
								Absences :
								<span class="badge">
									<c:out value="${fn:length(missings.get(group.getName()))}"></c:out>
								</span>
							</div>
							<div class="medal">
								<span class="fa fa-folder-open"></span>
								Livrables :
								<span class="badge">0</span>
							</div>
							<div class="list-group">
							  <c:forEach var="student" items="${group.getMembers()}" varStatus="stdItem">
							  	<span class="list-group-item">
							  		<c:out value="${student.getFirstName()}"></c:out>
							  		<c:out value="${student.getLastName()}"></c:out> - 
							  		<a href="Students?pseudo=${student.getPseudo()}"><em><c:out value="${student.getPseudo()}"></c:out></em></a>
							  	</span>
							  </c:forEach>
							</div>
						</div>
					</div>
			</c:forEach>
		</div>
		<hr/>
	</c:forEach>
</div>
<div class="col-md-3" role="complementary">
	<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix-top">
		<ul id="sidebar" class="nav nav-stacked">
			<c:forEach var="cls" items="${allClass}" varStatus="clItem">
				<li>
					<a href="#Group${cls}">Classe <c:out value="${cls}"></c:out></a>
					<ul class="nav nav-stacked" >
					<c:forEach var="group" items="${prom.get(cls)}" varStatus="grItem">
						<c:if test="${group.get_class() == cls}">
							<li><a href="#Group${group.getName()}"><c:out value="${group.getName()}"></c:out></a></li>
						</c:if>
					</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
	</nav>
</div>