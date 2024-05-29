<%@page import="domain.User"%>
<%@page import="dto.UserPost"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserPosts"%>
<%!
	String name;
	List<UserPost> userPosts;
	User loggedUser;
%>
<% loggedUser = (User)request.getSession().getAttribute("user"); 
	if(!loggedUser.isAdmin()){
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"rel="stylesheet">
<link href="<%= request.getContextPath()%>/css/home.css" rel="stylesheet">
<title>Home Page</title>
</head>



<body>

	<main>
		<div class="row">
			<div class="col-md-9 offset-md-1">
				<h1><span>WELCOME!!!</span> ${sessionScope.user.name }</h1>
				
				<div class="row" id="postCreateRow">
					<div class="col-md-6 offset-md-3">
						<form action="<%= request.getContextPath()%>/post" method="post">
							 <div class="form-group">
    							<label for="postCreate">Create post</label>
    							<textarea class="form-control" id="postContent" rows="3" name="postContent"></textarea>
    							<input type="hidden" name="name" value="${sessionScope.name }">
  							</div>
  							<br>
  							<button type="submit" class="btn btn-light" name="action" value="create">Post</button>
						</form>
					</div>
				</div>
				<hr>
				<br>
				<br>
				<div class="row">
					<div class="col-md-6 offset-md-3">
						
						<% userPosts = (List<UserPost>)request.getAttribute("userPosts");
							if(userPosts != null) {
								for(UserPost post : userPosts) { %>
								<div>
									<span><%= post.getPost()%></span> : <span style="font-size:10px; color:lightgray;"><%= post.getName()%></span>
								</div>
								<% if(loggedUser.getName().equalsIgnoreCase(post.getName())) {%>
								<div class="row">
									<div class="col-md-2 offset-md-10">
										<div class="postActions">
											<a href="" class="edit">Edit</a>
											<a href="<%= request.getContextPath()%>/post?name=<%= request.getParameter("name")%>&action=delete&postId=<%=post.getPostId()%>" class="delete">Delete</a>
										</div>
									</div>
								</div>
								<%} %>
								<hr>
						<%		} 
							}
						%>
												
						
					</div>				
				</div>
			</div>
			<div class="col-md-2">
				<a href="<%= request.getContextPath()%>/user?action=logout"><button type="button" class="btn btn-secondary">Log out</button></a>
				<hr style="width:50%">
				<a href="<%= request.getContextPath()%>/user?action=respass"><button type="button" class="btn btn-outline-secondary">Reset password</button></a>
				<br>
				<br>
				<a href="<%= request.getContextPath()%>/user?action=delete"><button type="button" class="btn btn-danger">Delete account</button></a>
			</div>
		</div>
	</main>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
<%}
%>