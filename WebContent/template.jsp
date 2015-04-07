<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<c:if test="${sessionScope.user == null}">
	<c:redirect url="/Signin" />
</c:if>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<jsp:useBean id="pages" type="org.purple.bean.Page" scope="request" />

<!-- On affiche tous les css -->

<c:forEach var="currentName" items="${pages.getCss()}"
	varStatus="status">
	<link rel="stylesheet" href="./css/<c:out value="${currentName}"/>">
</c:forEach>

</head>
<body>
	<header class="container-fluid">
		<h1>Isep App</h1>
		<c:import url="jsp/nav_${sessionScope.user.position}.jsp"></c:import>
	</header>

	<div id="content" class="container-fluid">
		<c:import url="jsp/${pages.getContent()}"></c:import>
	</div>

	<footer> </footer>
	<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="./js/bootstartp.min.js"></script>

	<!-- On affiche tous les js -->
	<c:forEach var="currentName" items="${pages.getJs()}"
		varStatus="status">
		<script type="text/javascript"
			src="./js/<c:out value="${currentName}"/>"></script>
	</c:forEach>

</body>
</html>