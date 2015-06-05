<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<div class="row">
		<h1 class="col-xs-offset-1 col-xs-10">Les Deadlines</h1>
	</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>

<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
		<em style="color:#337AB7">Les deadlines</em>
			<div class="latest-group list">
			<input type="hidden" value="${fn:length(deadline)}" id="taille"/>
			<form class="form-inline off deadlineDate" method="post" action="Deadlines">
				<div class="form-group button">
					<a class="btn btn-default modify" href="#" role="button"><span class="fa fa-pencil"></span> <span>Modifier</span></a>
					<button type="submit" class="btn btn-default" id="valid"><span class="fa fa-check"> </span> Valider</button>
				</div>
				<input type="hidden" value="${fn:length(deadline)}" name="number"/>
<%-- 					<c:forEach var="i" begin="0" end='${fn:length(deadline)-1}'> --%>
<!-- 					<div class="alert alert-info design"> -->
<%-- 						<input type="hidden" value="${deadline[i].getStatus()}" id="status${i}"/> --%>
<%-- 						<input type="hidden" value="${deadline[i].getId()}" name="id${i}"/> --%>
<!-- 							<div class="form-group deadline"> -->
<%-- 							Groupe: <strong><c:out value="${deadline[i].getGroup()}"></c:out> </strong>  --%>
<!-- 							</div> -->
<!-- 							<div class="form-group deadline"> -->
<%-- 							Titre:  <strong><c:out value="${deadline[i].getDescription()}"></c:out> </strong>  --%>
<!-- 							</div> -->
<!-- 							<div class="form-group deadline"> -->
<%-- 								<c:forEach var="j" begin="0" end='${fn:length(user)-1}'> --%>
<%-- 								 	<c:if test="${user[j].getGroup() == deadline[i].getGroup()}" >  --%>
<%-- 							Tuteur: <strong><c:out value="${user[j].getFirstName()}"></c:out> <c:out value="${user[j].getLastName()}"></c:out> </strong> --%>
<%-- 									</c:if> --%>
<%-- 								</c:forEach>  --%>
<!-- 							</div>  -->
<!-- 							<div class="form-group deadline"> -->
<%-- 								Date:<input type="date" class="form-control dateDisabled"  name="datelim${i}" id="datelim${i}" value="${deadline[i].getDateLimit().toString('yyyy-MM-dd')}"/> --%>
<!-- 							</div> -->
<!-- 							<div class="form-group"> -->
<%-- 								Heure:<input type="time" class="form-control dateDisabled"  name="timelim${i}"  id="timelim${i}"value="${deadline[i].getDateLimit().toString('HH:mm:ss')}"/> --%>
<!-- 							</div> -->
						
<!-- 					</div> -->
<%-- 					</c:forEach> --%>

							<c:if test="${empty deadline}">
								<div class="alert alert-danger design">
									<div class="form-group deadline">
												Il n'y a pas de deadline.
									</div>
								</div>
							</c:if>
					<c:forEach var="deadline" items="${deadline}" varStatus="status">
						<div class="alert alert-info design">
							<input type="hidden" value="${deadline.getStatus()}" id="status${status.count}"/>
							<input type="hidden" value="${deadline.getId()}" name="id${status.count}"/>
								<div class="form-group deadline">
								Groupe: <strong><c:out value="${deadline.getGroup()}"></c:out> </strong> 
								</div>
								<div class="form-group deadline">
								Titre:  <strong><c:out value="${deadline.getDescription()}"></c:out> </strong> 
								</div>
								<div class="form-group deadline">
									<c:forEach var="j" begin="0" end='${fn:length(user)-1}'>
									 	<c:if test="${user[j].getGroup() == deadline.getGroup()}" > 
								Tuteur: <strong><c:out value="${user[j].getFirstName()}"></c:out> <c:out value="${user[j].getLastName()}"></c:out> </strong>
										</c:if>
									</c:forEach> 
								</div> 
								<div class="form-group deadline">
									Date:<input type="date" class="form-control dateDisabled"  name="datelim${status.count}" id="datelim${status.count}" value="${deadline.getDateLimit().toString('yyyy-MM-dd')}"/>
								</div>
								<div class="form-group">
									Heure:<input type="time" class="form-control dateDisabled"  name="timelim${status.count}"  id="timelim${status.count}"value="${deadline.getDateLimit().toString('HH:mm:ss')}"/>
								</div>
							</div>
						</c:forEach>
				</form>
			</div>
	</div>
</div>