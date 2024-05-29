<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"rel="stylesheet">
<link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>

		<header>
			<div class="row" id="header-row">
				<div class="col-md-3 offset-md-9">
					<a href="pages/login.jsp"><button type="button" class="btn btn-primary">Log in</button></a>
					<a href="pages/signup.jsp"></a><button type="button" class="btn btn-outline-secondary">Sign up</button>
				</div>
			</div>
		</header>
		
		<main>
			<div class="row">
				<div class="col-md-6 offset-md-3">
					<h1>Welcome to <br><span>THE SOCIAL NETWORK</span></h1>
				</div>
			</div>
		</main>

<!--  
<footer>
	<p>FOOTER</p>
</footer>
-->
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>