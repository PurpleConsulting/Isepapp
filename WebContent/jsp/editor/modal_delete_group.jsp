<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-horizontal" id="delete-grp" method="post">
	<div style="margin-left:10%; width:80%">
		<p>
			Voulez vous <u style="color:#A94442;">vraiment</u> supprimer <u style="color:#A94442;">tout</u>
			ce groupe?  <span class="fa fa-exclamation-triangle"></span><br/>
			La suppression d'un group entier entrainera la suppréssion<br/>
			<ul>
			<li>de chacun des membres du groupe</li>
 			<li>de <strong>toutes</strong> les notes</li>
 			<li>de <strong>toutes</strong> leurs abscences</li>
 			<li>et de leurs livrables</li>
			</ul>
			Si même après avoir vu cette petite icon: <span class="fa fa-exclamation-triangle"></span><br/>
			vous voulez poursuivre la suppréssion du groupe vous pouver recopier le nom du groupe:
			<strong><em class="todel"> </em></strong>, ici.
		</p>
	</div>
	<div class="form-group has-error" style="margin-left:10%; width:80%">
		<input name="suggestion-grp" class="col-xs-10 form-control delete-std" type="text"/>
	</div>
</form>