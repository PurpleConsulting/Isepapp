<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${pages.getWarning()}">
	<div class="row">
		<div class="alert alert-warning col-xs-offset-1 col-xs-10">
			<strong>Attention</strong>,
			<c:out value="${pages.getWarningMessage()}"></c:out>
		</div>
	</div>
</c:if>
<c:if test="${pages.getError()}">
	<div class="row">
		<div class="alert alert-danger col-xs-offset-1 col-xs-10">
			<strong>Attention</strong>,
			<c:out value="${pages.getErrorMessage()}"></c:out>
		</div>
	</div>
</c:if>
<c:if test="${pages.getInfo()}">
	<div class="row">
		<div class="alert alert-info col-xs-offset-1 col-xs-10">
			<strong>Attention</strong>,
			<c:out value="${pages.getInfoMessage()}"></c:out>
		</div>
	</div>
</c:if>
<c:if test="${pages.getSuccess()}">
	<div class="row">
		<div class="alert alert-success col-xs-offset-1 col-xs-10">
			<strong>Félicitation : </strong>
			<c:out value="${pages.getSuccessMessage()}"></c:out>
		</div>
	</div>
</c:if>
