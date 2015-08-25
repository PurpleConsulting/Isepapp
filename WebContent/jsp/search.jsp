<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1 class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
	Page de Recherche
	<small data-target="${keyword}"> - <c:out value="${keyword}"></c:out></small>
</h1>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row result">

		<div class="col-xs-offset-1 col-xs-10">
		<h4 class="">Recherche de groupe</h4>
		<c:choose>
		<c:when test="${empty groups}">
			<div class="col-xs-6 col-xs-offset-3">
				<img src="img/empty/group.svg" alt="" class="app-empty-img"/>
			</div>
		</c:when>
		<c:otherwise>
			<table class="table table-hover">
				<caption>Groupe du semestre.</caption>
				<thead>
					<tr>
						<th>#</th>
	          			<th>Groupe</th>
	          			<th>Tuteur</th>
	          			<th>n° étudiants</th>
	        		</tr>
				</thead>
				<tbody>
				<c:forEach var="group" items="${groups}" varStatus="status">
				<tr>
					<th scope="row"><c:out value="${status.count}"></c:out></th>
	          		<td><c:out value="${group.value.getName()}"></c:out></td>
	          		<td><c:out value="${group.value.getTutor()}"></c:out></td>
	          		<td><c:out value="${fn:substring(group.key,4,8)}"></c:out></td><!-- fn:substring( -->
	          	</tr>
	       		</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
		</c:choose>
	</div>
	
	<div class="col-xs-offset-1 col-xs-10">
		<h4 class="">Recherche de d'étudiants</h4>
		<c:choose>
		<c:when test="${empty students }">
			<div class="col-xs-6 col-xs-offset-3">
				<img src="img/empty/student.svg" alt="" class="app-empty-img"/>
			</div>
		</c:when>
		<c:otherwise>
		<table class="table table-hover">
	      <caption>Elèves de la promotion.</caption>
	      <thead>
	        <tr>
	          <th>#</th>
	          <th>Prénom</th>
	          <th>Nom</th>
	          <th>Groupe</th>
	        </tr>
	      </thead>
	      <tbody>
	      <c:forEach var="student" items="${students}" varStatus="status">
	      	<tr>
	          <th scope="row"><c:out value="${status.count}"></c:out></th>
	          <td><c:out value="${student.value.getFirstName()}"></c:out></td>
	          <td><c:out value="${student.value.getLastName()}"></c:out></td>
	          <td><c:out value="${student.value.getGroup()}"></c:out></td>
	        </tr>
	      </c:forEach>
	      </tbody>
	    </table>
	    </c:otherwise>
	    </c:choose>
	</div>	
</div>