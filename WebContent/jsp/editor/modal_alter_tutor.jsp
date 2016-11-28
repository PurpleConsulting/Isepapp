<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="alter-tutor" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Avec ce formulaire vous pouvez modifier les informations liées au tuteur : 
			<u class="u-target" style="color:#337AB7;"></u>. Le choix d'une classe ici,
			remplacera tous les groupes du tuteur. Pour ajouter à ce tuteur des groupes en <em>plus</em>
			vous pouvez le faire manuellement via les pages groupes.
		</p>
	</div>
	<div class="form-group" style="margin-left:10%; width:80%">
		<input type="hidden" name="update_pseudo" class="form-control" />
		<input type="text" name="update_first_nane" placeholder="Prénom du tuteur" class="form-control"/>
		<input type="text" name="update_last_name" placeholder="Nom du tuteur" class="form-control"/>
		<input type="email" name="update_email" placeholder="Email tuteur" class="form-control"/>
		<input type="password" name="update_password" placeholder="Mot de passe" class="form-control" 
		style="display:inline-block; width:90%" disabled/>
		<input type="checkbox" name="has-pass">
		<select name="update_group">
		</select>
	</div>
</form>