<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="alter-tutor" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Avec ce formulaire vous pouvez modifier les informations liées au tuteur: 
			<u class="u-target" style="color:#337AB7;"></u><br/>
		</p>
	</div>
	<div class="form-group" style="margin-left:10%; width:80%">
		<input type="text" name="new_first_mane" placeholder="Prénom du tuteur" class="form-control"/>
		<input type="text" name="new_last_name" placeholder="Nom du tuteur" class="form-control"/>
		<input type="email" name="new_email" placeholder="Email tuteur" class="form-control"/>
		<input type="password" name="" placeholder="Mot de passe" class="form-control"/>
	</div>
</form>