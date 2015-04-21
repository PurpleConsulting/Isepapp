<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-offset-1 col-md-10" style="height:600px">
	<c:if test="${pages.getWarning()}">
		<div class="alert alert-warning col-md-offset-1 col-md-10">
			<strong>Attention</strong>,
			<c:out value="${pages.getWarningMessage()}"></c:out>
		</div>
	</c:if>
	<c:if test="${pages.getError()}">
		<div class="alert alert-danger col-md-offset-1 col-md-10">
			<strong>Attention</strong>,
			<c:out value="${pages.getErrorMessage()}"></c:out>
		</div>
	</c:if>
</div>