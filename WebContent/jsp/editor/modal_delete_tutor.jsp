<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="delete-tutor" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Voulez vous <u style="color:#A94442;">vraiment</u> supprimer ce tuteur? 
			<span class="fa fa-exclamation-triangle"></span><br/><br/><em>
			Note: Le groupe et les notes donnés ne seront pas supprimées.</em><br/>
			Pour confirmer la suppréssion de l'utilisateur veullez recopier sont pseudo:
			<strong class="st-target" style="color:#A94442;"></strong> , ici
		</p>
	</div>
	<div class="form-group has-error" style="margin-left:10%; width:80%">
		<input value="" name="del-tutor" class="col-xs-10 form-control" type="text"/>
		<input value="delete" name="delete-one-tutor" class="col-xs-10 form-control" type="hidden"/>
	</div>
</form>