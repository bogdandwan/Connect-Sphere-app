<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"rel="stylesheet">
<link href="<%= request.getContextPath()%>/css/login.css" rel="stylesheet">
<title>Log in</title>
</head>
<body>
	
	<div class="container">
		<header>
			<div class="row" id="login-header-container">
				<div class="col-md-3 offset-md-9">
					<a href="<%= request.getContextPath()%>/index.jsp"><button type="button" class="btn btn-outline-secondary">Back to Index</button></a>
				</div>
			</div>
		</header>
		
		<main>
			<div class="row" id="login-container">
			<div class="col-md-4 offset-md-4">
				<form action="<%= request.getContextPath()%>/user" method="post">
					<div class="form-group">
						<label for="email">Email adress</label>
						<input type="text" class="form-control" id="email" name="email" aria-describedby="email" placeholder="Enter email" value="${param.email }">
					</div>
					
					<br>
					
					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
					</div>
					
					<br>
					
					
					<br>
					
					<button type="submit" class="btn btn-primary" name="action" value="login">Log in</button>
				</form>
			</div>
		</div>
		
		<br>
		
		<div class="row">
			<div class="col-md-4 offset-md-4" id="signUpErrMsgHolder">
				<span>${requestScope.errMsg }</span>
			</div>
		</div>
		</main>
		
	</div>
	
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>