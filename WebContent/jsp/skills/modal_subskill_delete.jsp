<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form action="" class="form-horizontal form-add-skill" id="modal-form-delsubskill" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Voulez vous <u style="color:#A94442;">vraiment</u> supprimer <u style="color:#A94442;">cette</u>
			sous-compétence?  <span class="fa fa-exclamation-triangle" style="color:#A94442;"></span><br/>
			La suppression d'une compétence entrainera la suppréssionde<br/>
			<strong>toutes</strong> les notes associées.
			Si vous êtes sûr de vouloir poursuivre la suppréssion,
			vous pouver recopier le nom de la sous-copétence:
			<strong><em class="todel"> </em></strong>, ici.
		</p>
	</div>
	<div class="form-group has-error" style="margin-left:10%; width:80%">
		<input name="suggestion-grp" class="col-xs-10 form-control delete-std" type="text"/>
	</div>
</form>