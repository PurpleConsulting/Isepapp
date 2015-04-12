<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<c:if test="${sessionScope.user != null}">
		<c:redirect url="/Home" />
	</c:if>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="./css/bootstrap.min.css">
		<title>ISEP/APP - Connection</title>
		<style>
			#formblock{
				margin-top: 120px;
				margin-bottom: 200px;
			}
			#formblock .alert-danger{
				margin-bottom:40px;
			}
			form{
				margin-top: 80px;
				border-radius: 4px;
				border: 0.5px #CCC solid;
				padding: 50px;
			}
			.alert-input{
				height: 34px;
				margin-top:24px;
				margin-bottom: 0px;
				padding-bottom: 0px;
			}
			.alert-input p{
				position: absolute;
				top: 6px;
				left: 10px;
			}
		</style>
	</head>
	<body>
		<div class="container-fluid">
			<div  id="formblock" class="col-md-offset-2 col-md-8">
			<div class="rox">
				<div class="alert alert-danger col-md-offset-1 col-md-10"> Some trouble, lot trouble... stuff and trouble </div>
			</div>
			<form method="post" action="/Isepapp/Signin">
				<div class="row">
				  <div class="form-group col-md-7 has-feedback">
				    <label for="id">Pseudo</label>
				    <input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Enter votre pseudo isep">
				  </div>
				  <div style="display:none" class="col-md-4 alert alert-warning alert-input" role="alert">
					<p><strong>Welcome</strong> loic ;)</p>
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