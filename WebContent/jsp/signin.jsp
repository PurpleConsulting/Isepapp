<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="./css/bootstrap.min.css">
		<title>Form</title>
	</head>
	<body>
		<div class="container-fluid">
			<h1>Form</h1>
			<div class="col-md-6">
			<form method="post" action="/Isepapp/Signin">
			  <div class="form-group">
			    <label for="id">Id</label>
			    <input type="text" class="form-control" name="id" id="id" placeholder="Enter id">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
			</div>
		</div>
	</body>
</html>