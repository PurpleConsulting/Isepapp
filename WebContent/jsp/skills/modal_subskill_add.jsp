<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form action="" class="form-horizontal form-add-skill" id="modal-form-addsubskill" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Cette section vous permet d'ajouter une sous-compétence à la compétence sélectionée au préalable.
			Elle sera ensuite visible par tous les utilisateurs de l'application et les tuteurs 
			pourrons attribuer une note pour cette sous-compétence.
		</p>
	</div>
	<div class="form-group" style="margin-left:10%; width:80%">
		<p><label for="new_subskill_title">Nouvelle sous-compétence :</label></p>
		<input id="new_subskill_title" name="new_subskill_title" class="col-xs-10 form-control" type="text" placeholder="Conception / réalisation" style="margin-bottom:6px;"/>
		<p><label for="new_subskill_desc">Description :</label></p>
		<textarea name="new_subskill_desc"  id="new_subskill_desc" class="col-xs-10 form-control" type="text" placeholder="Concevoir et réaliser une application informatique (site web)">
		</textarea>
		<input name="add_subskill_flag" value="" type="hidden"/>
	</div>
</form>