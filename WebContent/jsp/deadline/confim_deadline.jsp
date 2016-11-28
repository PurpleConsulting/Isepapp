<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div style="margin-left:10%; width:80%">
	<p>
		Voulez-vous vraiment supprimer la deadline : <strong><em class="dd-title"></em></strong> pour le groupe <strong><em class="dd-group"></em></strong>?
	</p>
	<form method="post" action="Deadlines" class="form-del" style="display:none;">
		<input type="text" name="delete" id="modal-input-del"></input>
		<input type="text" name="id_deadline" id="modal-input-id"></input>
	</form>
</div>