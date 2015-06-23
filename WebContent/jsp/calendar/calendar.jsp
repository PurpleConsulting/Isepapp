<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-10 col-xs-offset-1">Les agendas de la promotion</h1>
</div>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row" style="margin-top:50px">
	<div class="col-xs-10 col-xs-offset-1">
		<c:forEach var="group" items="${groups}" varStatus="status">
			<button type="button" class="btn btn-default group_button" data-group="${group}"><c:out value="${group}"></c:out></button>
		</c:forEach>
	</div>
</div>
<div class="row" style="margin-top:30px">
	<div class="col-xs-10 col-xs-offset-1">
		<button type="button" class="btn btn-primary" id="semester1">Semestre 1</button>
		<button type="button" class="btn btn-default col-sm-offset-8" id="semester2">Semestre 2</button>
	</div>
	<div class="col-xs-12 col-xs-offset-1" style="margin-top:10px">
		<button type="button" class="btn btn-default" id="validerForm" >Valider</button>
	</div>
</div>
<div class="row" style="margin-top:50px">
	<div id="premierSemester">
		<div class="col-sm-3 col-xs-offset-1">
			<div id="september" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3">
			<div id="october" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3">
			<div id="november" class="calendar col-sm-10"></div>
		</div>
			<div class="col-sm-3 col-xs-offset-1">
			<div id="december" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3">
			<div id="january" class="calendar col-sm-10"></div>
		</div>
	</div>
	<div id="deuxiemeSemester">
			<div class="col-sm-3 col-xs-offset-1">
			<div id="february" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3">
			<div id="march" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3">
			<div id="april" class="calendar col-sm-10"></div>
		</div>
			<div class="col-sm-3 col-xs-offset-1">
			<div id="may" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3">
			<div id="june" class="calendar col-sm-10"></div>
		</div>
	</div>
</div>
