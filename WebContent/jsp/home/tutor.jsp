<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	<h1 class="col-xs-offset-1 col-xs-10">
		Accueil
		<small> - <c:out value="${sessionScope.user.getPseudo()}"></c:out> - </small>
	</h1>
</div>
<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 group">
		<div id="alert-passwd" class="alert" data-target="${user.getPseudo()}" style="display:none">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<p>Vous êtes un tuteur externe? Vous avez la possibilité de changer votre mot de passe
			en cliquant sur, <a class="change-pass" href="#">changer votre mot de passe.</a></p>
			<div id="alert-passwd-form" class="" style="display:none"><!--  -->
				<div>
					<form class="form-inline" action="SeviceTuteurHandler" method="post" onsubmit="return false;">
						<div class="form-group">
							<input type="password" name="old-pwd" class="form-control" placeholder="Ancien Mdp">
							<input type="password" name="new-pwd" class="form-control" placeholder="Nouveau Mdp">
							<span class="fa fa-lock fa-2x"></span>
							<button class="btn btn-default">Modifier</button>
							<div class="alert-pwd alert alert-warning" style="display: none"></div>
						</div>
					</form>
				</div>
				<hr/>
			</div>
		</div>
		<em style="color:#337AB7">Vous groupes d'APP</em>
		<div id="line-grp-template" class="col-xs-12 " style="display: none;" >
			<div class="col-xs-12 col-sm-3"><em>Groupe:</em> <strong class="grp"><a></a></strong></div>
			<div class="col-xs-12 col-sm-3"><em><a href="#" data-info="missing">Absences:</a></em> <strong class="abs">12</strong></div>
			<div class="col-xs-12 col-sm-3"><em><a href="#" data-info="delivery">Livrables:</a></em> <strong class="del">2</strong></div>
			<div class="col-xs-12 col-sm-3"><em></em><span class="fa fa-clock-o"></span></div>
		</div>
	</div>
</div>
