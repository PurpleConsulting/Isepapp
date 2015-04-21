<!--  BODY PART STUDENT MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">Fiche étudiant<small> - <c:out value="${student.getLastName()} ${student.getFirstName()}"></c:out></small></h1>
<div class="row">	
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 idcard">
		<div class="col-md-offset-1 col-md-4 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10  ">
			<img src="./img/photo.jpg" alt="" />
		</div>
		<div class="col-md-offset-1 col-md-4 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
			<div class="cell"><p><span class="col-md-1">Nom:</span><span class="col-md-offset-2"><c:out value="${student.getLastName()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Prénom:</span><span class="col-md-offset-2"><c:out value="${student.getFirstName()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Groupe:</span><span class="col-md-offset-2"><a href="Group?id=<c:out value="${student.getGroup()}"></c:out>"><c:out value="${student.getGroup()}"></c:out></a></span></p></div>
			<div class="cell"><p><span class="col-md-1">Isepid:</span><span class="col-md-offset-2"><c:out value="${student.getPseudo()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Email:</span><span class="col-md-offset-2"><c:out value="${student.getMail()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Tel:</span><span class="col-md-offset-2"><c:out value="${student.getTel()}"></c:out></span></p></div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 mark" >
		<h4>Compétences</h4>
		<div role="tabpanel">
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">Global</a></li>
		    <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">Travail en groupe</a></li>
		    <li role="presentation"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">Communication</a></li>
		    <li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">Conduite de projet</a></li>
		  	<li role="presentation"><a href="#tab5" aria-controls="tab5" role="tab" data-toggle="tab">Conception/réalisation</a></li>
		  	<li role="presentation"><a href="#tab6" aria-controls="tab6" role="tab" data-toggle="tab">Professionnel responsable</a></li>
		  </ul>
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="tab1">tab1</div>
		    <div role="tabpanel" class="tab-pane" id="tab2">tab2</div>
		    <div role="tabpanel" class="tab-pane" id="tab3">tab3</div>
		    <div role="tabpanel" class="tab-pane" id="tab4">tab4</div>
		    <div role="tabpanel" class="tab-pane" id="tab5">tab5</div>
		    <div role="tabpanel" class="tab-pane" id="tab6">tab6</div>
		  </div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 crossmark" >
		<h4>Evaluation croisée - 12 / 20</h4>
		<div class="table-responsive">
		<table class="table table-hover">
			<tr>
				<td>Travail en groupe</td>
				<td>Communication</td>
				<td>Conduite de projet</td>
				<td>Conception/réalisation</td>    
			</tr>
			<tr>
				<td>Noémie: acquis</td>
				<td>Noémie: acquis</td>
				<td>Noémie: acquis</td>
				<td>Noémie: acquis</td>
			</tr>
			<tr>
				<td>Emie: acquis</td>
				<td>Emie: acquis</td>
				<td>Emie: acquis</td>
				<td>Emie: acquis</td>
			</tr>
			<tr>
				<td>Amélie: acquis</td>
				<td>Amélie: acquis</td>
				<td>Amélie: acquis</td>
				<td>Amélie: acquis</td>
			</tr>
			<tr>
				<td>Naomie: acquis</td>
				<td>Naomie: acquis</td>
				<td>Naomie: acquis</td>
				<td>Naomie: acquis</td>
			</tr>
			<tr>
				<td>Noémie: acquis</td>
				<td>Emie: acquis</td>
				<td>Amélie: acquis</td>
				<td>Naomie: acquis</td>
			</tr>
		</table>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 missing" >
		<h4>Les absences - 36</h4>
		<br/>
		<c:set var="rowNum" scope="request" value="${1}"/>
		<c:forEach var="missingRow" items="${missingGrid}" varStatus="status">
			<div id="blk${rowNum}" ${rowNum == 1 ? 'class="active"' : ''}>
				<c:forEach var="missingLine" items="${missingRow}" varStatus="status">
					<c:if test="${missingLine != null }">
						<div class="alert alert-${missingLine.getLate() ? 'info' : 'warning'}" role="alert">
							<strong><c:out value="${missingLine.getLate() ? 'Retard' : 'Absence'}"></c:out></strong>:
							<c:out value="${missingLine.getDate()}"></c:out>, 
							<c:out value="${missingLine.getSupporting()}"></c:out>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<c:set var="rowNum" scope="request" value="${rowNum + 1}"/>
		</c:forEach>
		<nav>
		  <ul class="pagination">
		    <li>
		      <a data-target="1" href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:set var="rowNum" scope="request" value="${1}"/>
		    <c:forEach var="missingRow" items="${missingGrid}" varStatus="status">
		    	<li ${rowNum == 1 ? 'class="active"' : ''}>
		    		<a data-target="${rowNum}" href="#">${rowNum}</a>
		    	</li>
		    	<c:set var="rowNum" scope="request" value="${rowNum + 1}"/>
		    </c:forEach>
		    <li>
		      <a data-target="3" href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 delivery">
		<h4>Les livrables</h4>
		<div class="col-md-3 col-sm-6" >
			<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Ok: Rendu le 24/03">CR d'avancement</button>
		</div>
		<div class="col-md-3 col-sm-6" >
			<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Ok: Rendu le 02/05">Livrale toto</button>
		</div>
		<div class="col-md-3 col-sm-6" >
			<button type="button" class="btn btn-default" data-toggle="" data-placement="right" title="">Résumé tutu</button>
		</div>
	</div>
</div>
