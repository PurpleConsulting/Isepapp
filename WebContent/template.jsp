<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="org.purple.bean.User" %>
<!doctype html>
<c:if test="${sessionScope.user == null}">
	<c:redirect url="/Signin" />
</c:if>
<c:if test="${pages == null}">
	<% User u = (User)session.getAttribute("user");%>
	<jsp:useBean id="pages" class="org.purple.bean.Page"></jsp:useBean>
	<% pages.setCss("bootstrap-select.min.css", "../js/bootstrap-fileinput/css/fileinput.min.css", "home_"+  u.getPosition() +".css"); %>
	<% pages.setJs("bootbox.min.js", "bootstrap-select.min.js", "bootstrap-fileinput/js/fileinput.min.js", "bootstrap-fileinput/js/fileinput_locale_fr.js", "home_"+  u.getPosition() +".js"); %>
	<jsp:setProperty property="content" value="home/common.jsp" name="pages"  />
	<jsp:setProperty property="title" value="ISEP / APP  Accueil" name="pages"  />
</c:if>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><c:out value="${pages.getTitle()}"></c:out></title>
		<link rel="icon" type="image/png" href="./img/favicon_isep.png" />
		<link rel="stylesheet" href="./css/bootstrap.min.css">
		<link rel="stylesheet" href="./css/font-awesome.min.css">
		<link rel="stylesheet" href="./css/main.css">
		<!-- On affiche tous les css -->
		<c:forEach var="currentName" items="${pages.getCss()}" varStatus="status">
			<link rel="stylesheet" href="./css/<c:out value="${currentName}"/>">
		</c:forEach>
	</head>
	<body>
		<header>
			<div class="container-fluid">
				<div class="col-sm-offset-1">
					<img class="" src="img/BANNERISEPAPP.svg"  alt ="Bannière du site Isep.app" style="heigth:150px;"/><!--  style="width:40%;"-->
				</div>
			</div>
		</header>
		<c:import url="jsp/nav/nav_${sessionScope.user.position}.jsp" charEncoding="UTF-8"></c:import>
		<div id="content" class="container-fluid content">
			<c:import url="jsp/${pages.getContent()}" charEncoding="UTF-8"></c:import>
		</div>
		<footer> </footer>
		<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
		<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
		<script type="text/javascript" src="./js/typeahead.bundle.min.js"></script>
		<script type="text/javascript" src="./js/main.js"></script>
		<!-- On affiche tous les js -->
		<c:forEach var="currentName" items="${pages.getJs()}" varStatus="status">
			<script type="text/javascript" src="./js/<c:out value="${currentName}"/>"></script>
		</c:forEach>
	</body>
</html>