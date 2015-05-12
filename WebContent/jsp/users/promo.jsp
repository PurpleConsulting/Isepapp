<!--  BODY PART PROM MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="prom" class="col-md-offset-1 col-md-8">
	<h1>La promotion</h1>
	<div class="info-checkpoint">
		<div class="text">
			<h5> -- Page promo: La notice --</h5>
			Vous trouverez ici l'ensemble des groupes  pour tout un semestre.<br/>
			Les informations sur leur tutor, note et absences sont disponibles sur cette page.<br/>
			Les groupes sont regroupés pas classe, n'hésitez pas à utiliser l'ascenseur sur votre droite
			pour naviguer. l'agencement de la page est le suivant:
		</div>
	</div>
	<div class="row subgroup">
			├ ── Page Promotion:<br/>
			├   ├ ── Class 1:<br/>
			├   ├   ├ ── Info Tutor<br/>
			├   ├   ├ ── Groupe A<br/>
			├   ├   ├ ── Groupe B .... <br/>
			...<br/>
			├   ├ ── Class 2:<br/>
			... etc.<br/>
	</div>
	<c:forEach var="cls" items="${allClass}" varStatus="clItem">
		<div id="Group${cls}" class="group">
			<h2>Classe <c:out value="${cls}"></c:out></h2>
			<div class="class-checkpoint">
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
				Donec quis nibh velit. Mauris sollicitudin in diam ac imperdiet. 
				Vivamus elementum ultrices turpis vel sollicitudin. In hac habitasse platea dictumst. 
				Vivamus suscipit imperdiet urna elementum consectetur. 
				Cras commodo lorem justo, vel egestas urna malesuada at.
			</div>
			<c:forEach var="group" items="${groups}" varStatus="grItem">
				<c:if test="${group.get_class() == cls}">
					<div id="Group${group.getName()}" class="row subgroup">
						<div class="col-sm-10">
							<h3>Groupe <c:out value="${group.getName()}"></c:out></h3>
							<div class="medal">
								<span class="fa fa-graduation-cap"></span>
								Note:
								<span class="badge">0</span>
							</div>
							<div class="medal">
								<span class="fa fa-bed"></span>
								Absences:
								<span class="badge">0</span>
							</div>
							<div class="medal">
								<span class="fa fa-folder-open"></span>
								Livrables:
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
				</c:if>
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
					<c:forEach var="group" items="${groups}" varStatus="grItem">
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