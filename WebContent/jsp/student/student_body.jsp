<!--  BODY PART STUDENT MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--<c:out value="${average.getTitle()}"></c:out>: ===> <c:out value="${average.compute()}"></c:out><br/>
<c:forEach var="skill_mark" items="${average.grid}" varStatus="status">
- - - ><c:out value="${skill_mark.getTitle()}"></c:out>: <c:out value="${skill_mark.compute()}"></c:out><br/>
<c:forEach var="sub_skill_mark" items="${skill_mark.grid}" varStatus="status">
- - - - - - ><c:out value="${sub_skill_mark.getSubSkill()}"></c:out> => <c:out value="${sub_skill_mark.getTitle()}"></c:out>: <c:out value="${sub_skill_mark.compute()}"></c:out><br/>
</c:forEach>
</c:forEach>-->
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Fiche étudiant
	<small> - <c:out value="${student.getLastName()} ${student.getFirstName()}"></c:out></small>
	<a class="btn btn-default" href="#" role="button"><span class="fa fa-pencil"> </span>  Editer</a>
</h1>
<div class="row">	
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 idcard">
		<div class="col-md-offset-1 col-md-4 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10  ">
			<img src="./img/photo.jpg" alt="" />
		</div>
		<div class="col-md-offset-1 col-md-4 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
			<div class="cell"><p><span class="col-md-1">Nom:</span><span class="col-md-offset-2"><c:out value="${student.getLastName()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Prénom:</span><span class="col-md-offset-2"><c:out value="${student.getFirstName()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Groupe:</span><span class="col-md-offset-2"><a href="Groups?scope=<c:out value="${student.getGroup()}"></c:out>"><c:out value="${student.getGroup()}"></c:out></a></span></p></div>
			<div class="cell"><p><span class="col-md-1">Isepid:</span><span class="col-md-offset-2"><c:out value="${student.getPseudo()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Email:</span><span class="col-md-offset-2"><c:out value="${student.getMail()}"></c:out></span></p></div>
			<div class="cell"><p><span class="col-md-1">Tel:</span><span class="col-md-offset-2"><c:out value="${student.getTel()}"></c:out></span></p></div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 mark" >
		<h4>Compétences - <a class="btn btn-default" href="#" role="button"><span class="fa fa-pencil"> </span>  Noter</a></h4> 
		<div role="tabpanel">
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">Global</a></li>
		    <c:set var="rowNum" scope="request" value="${2}"/>
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<li role="presentation">
					<a href="#tab${rowNum}" aria-controls="tab3" role="tab" data-toggle="tab"><c:out value="${skill.getTitle()}"></c:out></a>
				</li>
				<c:set var="rowNum" scope="request" value="${rowNum + 1}"/>
			</c:forEach>	
		    </ul>
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="tab1">
		    	<div class="col-sm-offset-1 col-sm-2 global-average">
		    		<div><c:out value="${average.compute()}"></c:out></div>
		    	</div>
		    	<div class="col-sm-offset-4 col-sm-9">
    				<c:forEach var="skill_mark" items="${average.grid}" varStatus="status">
		    			<div>
		    				<div class="col-sm-4"><c:out value="${skill_mark.getTitle()}"></c:out>:</div>
		    				<span class="badge"><c:out value="${skill_mark.compute().intValue()}"></c:out></span>
							<div class="progress">
						  		<div class="progress-bar" role="progressbar" aria-valuenow="${skill_mark.compute()}" aria-valuemin="0" aria-valuemax="20" style="width: 60%;"></div>
							</div>
						</div>
					</c:forEach>
		    	</div>
		    </div>
		    <c:set var="rowNum" scope="request" value="${2}"/>
		    <c:forEach var="skill_mark" items="${average.grid}" varStatus="status">
		    	<div role="tabpanel" class="tab-pane" id="tab${rowNum}">
		    		<div class="alert alert-mark global">
		    		<span class="alert-mark-result">
		    			<c:out value="${skill_mark.getTitle()}"></c:out>: 
		    			<strong><c:out value="${skill_mark.compute()}"></c:out></strong>
		    		</span>
		    		</div>
		    		<c:forEach var="sub_skill_mark" items="${skill_mark.grid}" varStatus="status">
		    			<div class="alert alert-mark">
		    				<span><c:out value="${sub_skill_mark.getSubSkill()}">:</c:out></span>
		    				<span class="alert-mark-result">
		    					<c:out value="${sub_skill_mark.getTitle()}"></c:out> - 
		    					<c:out value="${sub_skill_mark.compute()}"></c:out>
		    				</span>
		    			</div>
		    		</c:forEach>
		    	</div>
		    	<c:set var="rowNum" scope="request" value="${rowNum + 1}"/>
			</c:forEach>
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
		<h4>Les absences - <c:out value="${fn:length(missingGrid)}"></c:out></h4>
		<br/>
		<c:set var="rowNum" scope="request" value="${1}"/>
		<div id="blk-missing"> <!-- " ${rowNum == 1 ? 'class="active"' : ''} -->
		<c:forEach var="missingRow" items="${missingGrid}" varStatus="status">
					<c:if test="${missingRow != null }">
						<div class="alert alert-${missingRow.getLate() ? 'info' : 'warning'}${rowNum <= 3 ? ' active' : ''}" 
							role="alert" id="blk${rowNum}">
							<strong><c:out value="${missingRow.getLate() ? 'Retard' : 'Absence'}"></c:out></strong>:
							<c:out value="${missingRow.getDate()}"></c:out>, 
							<c:out value="${missingRow.getSupporting()}"></c:out>
						</div>
					</c:if>
			<c:set var="rowNum" scope="request" value="${rowNum + 1}"/>	
		</c:forEach>
		</div>
		<c:if test="${fn:length(missingGrid) > 3 }">
			<nav>
			  <ul class="pagination">
			    <li>
			      <a data-target="1" href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <c:set var="rowNum" scope="request" value="${1}"/>
			    <c:forEach var="i" begin="1" end="${fn:length(missingGrid)}" step="3">
			    	<li ${rowNum == 1 ? 'class="active"' : ''}>
			    		<a data-target="${rowNum}" href="#">${rowNum}</a>
			    	</li>
			    	<c:set var="rowNum" scope="request" value="${rowNum + 1}"/>
			    </c:forEach>
			    <li>
			      <a data-target="${rowNum - 1}" href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
		</c:if>
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
