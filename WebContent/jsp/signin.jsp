<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<c:if test="${sessionScope.user != null}">
	<c:redirect url="/Home" />
</c:if>
<c:if test="${pages == null}">
	<jsp:useBean id="pages" class="org.purple.bean.Page"></jsp:useBean>  
	<jsp:setProperty property="title" value="ISEP / APP - Connection" name="pages"  />
</c:if>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><c:out value="${pages.getTitle()}"></c:out></title>
		<link rel="stylesheet" href="./css/bootstrap.min.css">
		<link rel="stylesheet" href="./css/font-awesome.min.css">
		<link rel="stylesheet" href="./css/main.css">
		<link rel="stylesheet" href="./css/signin.css">
	</head>
	<body>
		<header>
			<div class="container-fluid">

			</div>
		</header>
		<div class="container-fluid">
			<div  id="formblock" class="col-md-offset-2 col-md-8">
			<div class="row">
				<div class="col-xs-12">
					<c:import url="/jsp/alert.jsp" charEncoding="UTF-8"></c:import>
				</div>
			</div>
			<form method="post" action="/Isepapp/Signin">
				<div class="row">
				  <div class="form-group col-md-7 has-feedback">
				    <label for="id">Identifiant: </label>
				    <input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Ex: Luc MARTIN -> lmartin">
				  </div>
				  <div style="display:none" class="col-xs-offset-1 col-xs-10 col-md-offset-0 col-md-5 alert alert-warning alert_input" role="alert">

				</div>
				</div>
				<div class="row">
			  	<div class="form-group col-md-7">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
				  </div>
				</div>
			  <button type="submit" class="btn btn-default">Entrer</button>
			</form>
			</div>
		</div>
		<footer></footer>
		<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
		<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
		<script type="text/javascript" src="./js/signin.js"></script>
	</body>
</html>