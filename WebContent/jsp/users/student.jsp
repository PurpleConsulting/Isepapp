<!--  BODY PART STUDENT MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Fiche étudiant
	<small> - <c:out value="${student.getLastName()} ${student.getFirstName()}"></c:out></small>
	<a class="btn btn-default link-dialog-std" href="#" data-toggle="modal" data-target="#modal_alter" role="button"><span class="fa fa-pencil"> </span>  Editer</a>
</h1>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">	
	<div class="col-xs-offset-1 col-xs-10 idcard">
		<div class="col-md-offset-1 col-md-4 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10  ">
			<img src="./img/photo.jpg" alt="Photo de l'étudiant" />
		</div>
		<div class="col-md-offset-1 col-md-5 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
			<div class="cell"><p>
				<span class="col-xs-4 col-sm-2">Nom:</span>
				<span class="col-xs-4 col-sm-offset-1 col-md-offset-1"><c:out value="${student.getLastName()}"></c:out></span>
			</p></div>
			<div class="cell"><p>
				<span class="col-xs-4 col-sm-2">Prénom:</span>
				<span class="col-xs-4 col-sm-offset-1 col-md-offset-1"><c:out value="${student.getFirstName()}"></c:out></span>
			</p></div>
			<div class="cell"><p>
				<span class="col-xs-4 col-sm-2">Groupe:</span>
				<span class="col-xs-4 col-sm-offset-1 col-md-offset-1"><a href="Groups?scope=<c:out value="${student.getGroup()}"></c:out>"><c:out value="${student.getGroup()}"></c:out></a>
			</span></p></div>
			<div class="cell"><p>
				<span class="col-xs-4 col-sm-2">Isepid:</span>
				<span class="col-xs-4 col-sm-offset-1 col-md-offset-1"><c:out value="${student.getPseudo()}"></c:out></span>
			</p></div>
			<div class="cell"><p>
				<span class="col-xs-4 col-sm-2">Email:</span>
				<span class="col-xs-4 col-sm-offset-1 col-md-offset-1"><a href="mailto:${student.getMail()}"><c:out value="${student.getMail()}"></c:out></a></span>
			</p></div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 mark" >
		<h4>Compétences - <a class="btn btn-default" href="#" role="button"><span class="fa fa-pencil"> </span>  Noter</a></h4> 
		<div role="tabpanel">
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">Global</a></li>
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<c:if test="${skill.getId() != 0}">
				<li role="presentation">
					<a href="#tab${status.count + 1}" aria-controls="tab${status.count + 1}" role="tab" data-toggle="tab"><c:out value="${skill.getTitle()}"></c:out></a>
				</li>
				</c:if>
			</c:forEach>	
		    </ul>
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="tab1">
		    	<div class="col-sm-offset-1 col-sm-2 global-average">
		    		<div><c:out value="${fn:substring(average.compute(),0,4)}"></c:out></div><!--  -->
		    	</div>
		    	<div class="col-sm-offset-4 col-sm-9">
    				<c:forEach var="skill_mark" items="${average.grid}" varStatus="status">
		    			<c:if test="${!skill_mark.isCross()}">
		    			<div>
		    				<div class="col-sm-4"><c:out value="${skill_mark.getTitle()}"></c:out>:</div>
		    				<span class="badge"><c:out value="${skill_mark.compute().intValue()}"></c:out></span>
							<div class="progress">
						  		<div class="progress-bar" role="progressbar" aria-valuenow="${skill_mark.compute()}" aria-valuemin="0" aria-valuemax="20" style="width: 60%;"></div>
							</div>
						</div>
						</c:if>
					</c:forEach>
		    	</div>
		    </div>
		    <c:forEach var="skill_mark" items="${average.grid}" varStatus="status">
		    	<div role="tabpanel" class="tab-pane" id="tab${status.count + 1}">
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
			</c:forEach>
		  </div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 crossmark" >
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
	<div class="col-xs-offset-1 col-xs-10 missing" >
		<h4>Les absences - <c:out value="${fn:length(missingGrid)}"></c:out></h4>
		<br/>
		<div id="blk-missing">
		<c:forEach var="missingRow" items="${missingGrid}" varStatus="status">
			<c:if test="${missingRow != null }">
				<div class="alert alert-${missingRow.getLate() ? 'info' : 'warning'}${status.count <= 3 ? ' active' : ''}" 
					role="alert" id="blk${status.count}">
					<strong><c:out value="${missingRow.getLate() ? 'Retard' : 'Absence'}"></c:out></strong>:
					<c:out value="${missingRow.printDate()}"></c:out>, 
					<c:out value="${missingRow.getSupporting()}"></c:out>
					<c:if test="${missingRow.getLate()}">
						/ Heure d'arrivé: <strong><c:out value="${missingRow.printLate()}"/></strong>
					</c:if>
				</div>
			</c:if>
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
			    	<li ${i == 1 ? 'class="active"' : ''}>
			    		<a data-target="${rowNum}" href="#">${rowNum}</a>
			    	</li>
			    <c:set var="rowNum" scope="request" value="${rowNum + 1}"/>
			    </c:forEach>
			    <li>
			      <a data-target="${rowNum -1}" href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 delivery">
		<h4>Les livrables</h4>
		<c:forEach var="dead" items="${deadlines}" varStatus="ddElem">
			<div class="col-md-3 col-sm-6" >
				<c:choose>
    				<c:when test="${dead.getCompleted()}">
    					<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Ok, rendu le: ${dead.printDateLimit()}">
    						<c:out value="${dead.getDescription()}"></c:out>
    					</button>
    				</c:when>
    				<c:otherwise>
    					<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="">
    						<c:out value="${dead.getDescription()}"></c:out>
    					</button>
    				</c:otherwise>
    			</c:choose>
			</div>
		</c:forEach>
	</div>
</div>


<!-- INVISIBLE -->
<div id="modal_alter" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Modification du profil étudiant: <c:out value="${student.getPseudo()}"></c:out></h4>
			</div>
			<div class="modal-body">
				<form id="alteruser" class="form-horizontal" method="post" action="Students?pseudo=${student.getPseudo()}">
					<div class="form-group">
						<label for="std_first_name" class="col-sm-2 control-label">Prénom</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="std_first_name"
								placeholder="Prénom" value="${student.getFirstName()}">
						</div>
					</div>
					<div class="form-group">
						<label for="std_name" class="col-sm-2 control-label">Nom</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="std_last_name"
								placeholder="Nom" value="${student.getLastName()}">
						</div>
					</div>
					<div class="form-group">
						<label for="std_pseudo" class="col-sm-2 control-label">Pseudo</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="std_pseudo"
								placeholder="Pseudo ISEP, Julie LUTZ: jlutz" value="${student.getPseudo()}">
						</div>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="std_no" placeholder="n° Isep" value="${student.getIsepNo()}">
						</div>
					</div>
					<div class="form-group">
						<label for="std_email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="std_email"
								placeholder="Email" value="${student.getMail()}">
						</div>
					</div>
					<div class="form-group" ${sessionScope.user.getPosition() == 'respo' ? '' : 'style="display:none;"'}>
						<label for="std_email" class="col-sm-2 control-label">Groupe</label>
						<div class="col-sm-4">
							<select name="std_new_group" class="selectpicker">
								<c:forEach var="group" items="${availableGroups}" varStatus="status">
								<option value="${group}" ${group == student.getGroup() ? 'selected' : '' }><c:out value="${group}"></c:out></option>
								</c:forEach>
							</select>
						</div>
					</div>
					<button style="Display:none;" type="submit" class="btn btn-primary no-btn" id="addin"></button>
				</form>
			</div>
			<div class="modal-footer">
				<span style="display: none;"><input type="checkbox" /></span>
				<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
				<button onclick="$('form#alteruser').submit();" type="submit" class="btn btn-primary" id="alteruserbtn">Ajouter</button>
			</div>
		</div>
	</div>
</div>
