<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/signup.css"
	rel="stylesheet">
<title>Sign Up</title>
</head>
<body>

	<div class="container">
		<header>
			<div class="row" id="signup-header-container">
				<div class="col-md-3 offset-md-9">
					<a href="<%=request.getContextPath()%>/index.jsp"><button
							type="button" class="btn btn-outline-secondary">Back to
							Index</button></a>
				</div>
			</div>
		</header>
	</div>

	<main>
		<div class="row" id="signup-container">
			<div class="col-md-4 offset-md-4">
				<form action="<%=request.getContextPath()%>/user" method="post">
					<div class="form-group">
						<label for="email">Email adress</label> <input type="text"
							class="form-control" id="email" name="email"
							aria-describedby="email" placeholder="Enter email"
							value="${param.email }"> <small id="email"
							class="form-text text-muted">We'll never share your email
							with anyone else.</small>
					</div>

					<br>

					<div class="form-group">
						<label for="name">Name</label> <input type="text"
							class="form-control" id="name" name="name"
							aria-describedby="name" placeholder="Enter name"
							value="${param.name }">
					</div>

					<br>

					<div class="form-group">
						<label for="password">Password</label> <input type="text"
							class="form-control" id="password" name="password"
							placeholder="Enter password">
					</div>

					<br>

					<div class="form-group">
						<label for="passwordRetype">Re-type password</label> <input
							type="text" class="form-control" id="passwordRetype"
							name="passwordRetype" placeholder="Enter password">
					</div>
					<br>
					<div class="form-check">
						<input class="form-check-input" type="radio"
							name="userType" value="admin" id="flexRadioDefault1"> <label
							class="form-check-label" for="adminRadioButton"> Admin </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio"
							name="userType" value="user" id="flexRadioDefault2" checked> <label
							class="form-check-label" for="userRadioButton"> User </label>
					</div>

					<br>

					<button type="submit" class="btn btn-primary" name="action"
						value="signup">Sign Up</button>
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

</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>