<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-10 col-xs-offset-1">Les agendas de la promotion</h1>
</div>
<div class="col-xs-12">
	<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<div id="september" class="calendar col-sm-10"></div>
		<div id="october" class="calendar col-sm-10"></div>
	</div>
	<div class="col-xs-10 col-xs-offset-1">
		<div id="november" class="calendar col-sm-10"></div>
		<div id="december" class="calendar col-sm-10"></div>
	</div>
	<div class="col-xs-10 col-xs-offset-1">
		<div id="january" class="calendar col-sm-10"></div>
		<div id="february" class="calendar col-sm-10"></div>
	</div>
	
	<div class="col-xs-10 col-xs-offset-1">
		<div id="march" class="calendar col-sm-10"></div>
		<div id="april" class="calendar col-sm-10"></div>
	</div>
	<div class="col-xs-10 col-xs-offset-1">
		<div id="may" class="calendar col-sm-10"></div>
		<div id="june" class="calendar col-sm-10"></div> 
	</div>

</div>
