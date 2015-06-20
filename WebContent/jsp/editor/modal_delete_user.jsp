<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="delete-std" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Voulez vous <u>vraiment</u> supprimer cet étudiant?  <span class="fa fa-exclamation-triangle"></span><br/>
			La suppréssion entrainera automatiquement la suppréssion en cascade<br/>
			<ul>
 			<li>des <strong>notes</strong></li>
 			<li>des évaluations <strong>croisées</strong></li>
 			<li>et des autres informations liées à létudiant</li>
			</ul>
			Pour coufirmer la suppession copier le pseudo ISEP: <strong><em class="pop"> </em></strong>
		</p>
	</div>
	<div class="form-group has-error" style="margin-left:10%; width:80%">
		<input name="suggestion-std" class="col-xs-10 form-control delete-std" type="text"/>
	</div>
</form>