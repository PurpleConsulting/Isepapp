<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">
		Accueil
		<small  data-target="${user.getPseudo()}"> - <c:out value="${user.getPseudo()}"></c:out> - </small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1 home-delivery">
		<h4> - Les livrables du groupes - </h4>
		<span class="sub-h4"><em>Livrables déposés</em></span>
		<ul class="depots">
			<li data-role="exemple" >
				<a href="" download class="btn btn-default" data-toggle="tooltip" data-placement="right" title=""></a>
			</li>
		</ul>
		<span class="sub-h4"><em>Livrables à fournir</em></span>
		<ul class="deadline">
			<li data-role="exemple" >
				<a href="#" class="btn btn-primary"></a>
			</li>
		</ul>
		<span class="sub-h4"><em>Livrables en retard</em></span>
		<ul class="missing-depots">
			<li data-role="exemple" >
				<a href="#" class="btn btn-danger disabled"></a>
			</li>
		</ul>			
	</div>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1 home-fileinput" style="display:none;">
		<input data-role="exemple" name="delivery_file" type="file" class="file-loading">
		<div id="input-border" class="col-xs-12" style="padding:0px; margin:0px; display:none;">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-offset-1 home-missing">
		<h4> - Les Absences - </h4>
		<div data-role="exemple" class="alert" role="alert" id="blk">
				<strong></strong>:
				<span class="when"></span>, 
				<span class="why"></span>
		</div>
		<div id="blok-missing">
		</div>
		<nav class="missing-nav">
			<ul class="pagination">
			    <li>
			      <a data-target="1" href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li>
			      <a data-target="" href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		  	</ul>
		</nav>
	</div>
</div>