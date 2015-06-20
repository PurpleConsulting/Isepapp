<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="delete-all-tutors" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Voulez-vous <u style="color:#A94442;">vraiment</u> supprimer <u style="color:#A94442;">tous</u>
			les tuteurs de l'application ?  <span class="fa fa-exclamation-triangle"></span><br/><br/><em>
			Note : la suppression des tuteurs n'entraine, ni la suppression de leurs groupes, ni celle des notes qu'ils ont données.
			Ils ne seront en revanche plus capable de se connecter à l'application.</em>
		</p>
	</div>
	<div class="form-group has-error" style="margin-left:10%; width:80%">
		<input value="delete" name="delete-all-tutors" class="col-xs-10 form-control" type="hidden"/>
	</div>
</form>