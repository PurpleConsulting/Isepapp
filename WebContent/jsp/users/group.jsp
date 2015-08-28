<!--  BODY PART GROUP MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Fiche de groupe
	<small> - <c:out value="${group.getName()}"></c:out></small>
	<a class="btn btn-default" href="AlterGroups?scope=${group.getName()}" role="button"><span class="fa fa-pencil"> </span>  Editer</a>
</h1>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10 idcard">
		<div class="col-sm-12 label-group">
			<div class="col-sm-offset-0 col-sm-2" ><span class="fa fa-users fa-2x"></span>Groupe <c:out value="${group.getName()}"></c:out></div>
			<div class="col-sm-offset-1 col-sm-3"><span class="fa fa-user fa-2x"></span>Tuteur: <a href="Tutors#${group.getTutor()}"><c:out value="${group.getTutor()}"></c:out> </a>.</div>
			<div class="col-sm-offset-0 col-sm-2"><span class="fa fa-file-text fa-2x"></span> Moyenne: <strong><c:out value="${average.compute()}"></c:out> </strong></div>
			<div class="col-sm-offset-1 col-sm-2"><span class="fa fa-bed fa-2x"></span> Absences: <strong><c:out value="${fn:length(missings)}"></c:out> </strong></div>
		</div>
		<c:set var="abs" value="${0}"/>
		<c:forEach var="student" items="${group.getMembers()}" varStatus="stdElem">
			<c:forEach var="miss" items="${missings}" varStatus="missStat">
				<c:if test="${miss.getStudent() == student.getPseudo()}"><c:set var="abs" value="${abs + 1}"/></c:if>
			</c:forEach>
			<c:if test="${stdElem.index%2 == 0}"><div class="row noborder"></c:if>
			<div class="${stdElem.index%2 == 0 ? '' : 'col-sm-offset-0 col-md-offset-1'} col-md-5 col-sm-12 student-cell">
				<div class="picture">
					<img src="./img/photo.jpg" alt="" />
				</div>
				<div class="col-xs-offset-0 col-xs-12 col-sm-8">
					<span><c:out value="${student.getFirstName()} ${student.getLastName()}"></c:out> -
					<a href="Students?pseudo=${student.getPseudo()}">
						<em><c:out value="${student.getPseudo()}"></c:out></em>
					</a></span>
					<span><a href="mailto:${student.getMail()}">
						<c:out value="${student.getMail()}"></c:out>
					</a></span>
					<span>Absences: <span class="badge missing"><c:out value="${abs}"></c:out></span> </span>
					<span>Moyenne: <span class="badge mark">
							<c:out value="${average.byTitle(student.getPseudo()).compute()}"></c:out>	
						</span>
					</span>
				</div>
			</div>
			<c:if test="${stdElem.index%2 != 0 || stdElem.last}"></div></c:if>
			<c:set var="abs" value="${0}"/>
		</c:forEach>
	</div>
</div>

<div class="row">
	<div class="col-xs-offset-1 col-xs-10 missing" >
		<h4>Les absences - <c:out value="${fn:length(missings)}"></c:out></h4>
		<br/>
		<div id="blk-missing">
		<c:forEach var="missingRow" items="${missings}" varStatus="status">
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
		<c:if test="${fn:length(missings) == 0}">
			<div class="col-xs-6 col-xs-offset-3">
				<img src="img/empty/missing.svg" alt="" class="app-empty-img"/>
			</div>
		</c:if>
		</div>
		<c:if test="${fn:length(missings) > 3 }">
			<nav>
			  <ul class="pagination">
			    <li>
			      <a data-target="1" href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <c:set var="rowNum" scope="request" value="${1}"/>
			    <c:forEach var="i" begin="1" end="${fn:length(missings)}" step="3">
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
		<c:if test="${fn:length(deadlines) == 0}">
			<div class="col-xs-6 col-xs-offset-3">
				<img src="img/empty/delivery.svg" alt="" class="app-empty-img"/>
			</div>
		</c:if>
		<c:forEach var="dead" items="${deadlines}" varStatus="ddElem">
			<div class="col-md-3 col-sm-6" >
				<c:choose>
    				<c:when test="${dead.getCompleted()}">
    					<a download href="${dead.getDeliveryPath()}" type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Ok, rendu le: ${dead.printDateLimit()}">
    						<c:out value="${dead.getDescription()}"></c:out>
    					</a>
    				</c:when>
    				<c:otherwise>
    					<a href="#" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="">
    						<c:out value="${dead.getDescription()}"></c:out>
    					</a>
    				</c:otherwise>
    			</c:choose>
			</div>
		</c:forEach>
	</div>
</div>