<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form action="ManageSkills" class="form-horizontal form-add-skill" id="modal-form-delskill" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Voulez vous <u style="color:#A94442;">vraiment</u> supprimer <u style="color:#A94442;">cette</u>
			compétence?  <span class="fa fa-exclamation-triangle" style="color:#A94442;"></span><br/>
			La suppression d'une compétence entrainera la suppréssion<br/>
			<ul>
			<li>de <strong>toutes</strong> ses sous compétences</li>
 			<li>et de <strong>toutes</strong> les notes associées</li>
			</ul>
			Si vous êtes sûr de vouloir poursuivre la suppréssion,
			vous pouver recopier le nom de copétence:
			<strong><em class="todel"> </em></strong>, ici.
		</p>
	</div>
	<div class="form-group has-error" style="margin-left:10%; width:80%">
		<input name="del_skill" class="col-xs-10 form-control delete-std" type="text"/>
		<input name="del_skill_flag" value="" type="hidden"/>
	</div>
</form>