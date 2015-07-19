<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-10 col-xs-offset-1">Les agendas de la promotion
		<small>- <span class="fa fa-calendar"></span> -</small>
	</h1>
</div>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row" style="margin-top:50px">
	<div class="col-xs-10 col-xs-offset-1">
		<div style="display: block; margin:auto; width:250px;">
			<c:forEach var="group" items="${groups}" varStatus="status">
				<button type="button" class="btn btn-default group_button" data-group="${group}">
					<c:out value="${group}"></c:out>
				</button>
			</c:forEach>
		</div>
	</div>
</div>
<div class="row" style="margin-top:30px">
	<div class="col-xs-10 col-xs-offset-1">
		<button type="button" class="btn btn-primary" id="semester1">Semestre 1</button>
		<button type="button" class="btn btn-default col-sm-offset-8" id="semester2">Semestre 2</button>
	</div>
	<div class="col-xs-10 col-xs-offset-1" style="margin-top:10px">
		<button type="button" class="btn btn-default" id="validerForm"> <span class="fa fa-floppy-o"></span> Valider</button>
	</div>
</div>
<div class="row" style="margin-top:30px">
	<div class="col-xs-10 col-xs-offset-1 waiting">
		
	</div>
	<div class="" data-role="exemple" style="display: none">
		<div class="fa-spinner-box">
			<span class="fa fa-spinner fa-spin fa-4x"></span>
		</div>
		<div class="alert alert-info alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<strong>Et Voilà,</strong> le calendrier de la classe <strong data-target="class"></strong> à été mis à jour!
		</div>
	</div>
</div>
<div class="row" style="margin-top:30px">
	<div id="premierSemester">
		<div class="col-sm-3 col-xs-offset-1 calendar-set">
			<div id="september" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3 col-xs-offset-1 col-sm-offset-0 calendar-set">
			<div id="october" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3 col-xs-offset-1 col-sm-offset-0 calendar-set">
			<div id="november" class="calendar col-sm-10"></div>
		</div>
			<div class="col-sm-3 col-xs-offset-1 calendar-set">
			<div id="december" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3 col-xs-offset-1 col-sm-offset-0 calendar-set">
			<div id="january" class="calendar col-sm-10"></div>
		</div>
	</div>
	<div id="deuxiemeSemester">
		<div class="col-sm-3 col-xs-offset-1 calendar-set">
			<div id="february" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3 col-xs-offset-1 col-sm-offset-0 calendar-set">
			<div id="march" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3 col-xs-offset-1 col-sm-offset-0 calendar-set">
			<div id="april" class="calendar col-sm-10"></div>
		</div>
			<div class="col-sm-3 col-xs-offset-1 calendar-set">
			<div id="may" class="calendar col-sm-10"></div>
		</div>
		<div class="col-sm-3 col-xs-offset-1 col-sm-offset-0 calendar-set">
			<div id="june" class="calendar col-sm-10"></div>
		</div>
	</div>
</div>
