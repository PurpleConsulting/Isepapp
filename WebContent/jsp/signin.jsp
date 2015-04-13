<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<c:if test="${sessionScope.user != null}">
	<c:redirect url="/Home" />
</c:if>
<c:if test="${pages == null}">
	<jsp:useBean id="pages" class="org.purple.bean.Page"></jsp:useBean>   
</c:if>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ISEP / APP - Connection</title>
		<link rel="stylesheet" href="./css/bootstrap.min.css">
		<link rel="stylesheet" href="./css/font-awesome.min.css">
		<style>
			header{

				background-color: #246482;
				height: 160px;
				margin-bottom:10px;
			}
			header h1{
				color: white;
			}
			header h1, header span{
				display: inline-block;
				margin-right: 20px;
				margin-top: 20px;
			}
			span.fa{
				vertical-align: middle;
			}
			#formblock{
				margin-top: 10px;
				margin-bottom: 20px;
			}
			#formblock .alert-danger{
			
			}
			form{
				margin-top: 100px;
				border-radius: 4px;
				border: 0.5px #CCC solid;
				padding: 50px;
			}
			.alert_input{
				height: 34px;
				margin-top:24px;
				margin-bottom: 0px;
				padding-bottom: 0px;
			}
			.alert_input p{
				position: absolute;
				top: 6px;
				left: 10px;
			}
		</style>
	</head>
	<body>
		<header>
			<div class="container-fluid">
				<div class="col-md-offset-2">
					<span style="color:white;" class="fa fa-flag fa-5x"></span>
					<h1>ISEP - APP</h1>	
				</div>
			</div>
		</header>
		<div class="container-fluid">
			<div  id="formblock" class="col-md-offset-2 col-md-8">
			<div class="rox">
				<c:if test="${pages.getError()}">
					<div class="alert alert-danger col-md-offset-1 col-md-10">
						<strong>Attention</strong>,
						<c:out value="${pages.getErrorMessage()}"></c:out>
					</div>
				</c:if>
			</div>
			<form method="post" action="/Isepapp/Signin">
				<div class="row">
				  <div class="form-group col-md-7 has-feedback">
				    <label for="id">Identifiant: </label>
				    <input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Ex: Luc MARTIN -> lmartin">
				  </div>
				  <div style="display:none" class="col-md-4 alert alert-warning alert_input" role="alert">

				</div>
				</div>
				<div class="row">
			  	<div class="form-group col-md-7">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
				  </div>
				</div>
			  <button disabled type="submit" class="btn btn-default">Entrer</button>
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