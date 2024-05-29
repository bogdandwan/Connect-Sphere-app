package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;


@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PostController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//String postId = request.getParameter("postId");
		String name = request.getParameter("name");
		
		
		switch(action) {
		case "delete":
			PostService.deletePost(Integer.parseInt(request.getParameter("postId")));
			request.getRequestDispatcher("/home?name="+name).forward(request, response);
			break;
		}
	}

	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String postContent = request.getParameter("postContent");
		String action = request.getParameter("action");
		
		
		switch(action) {
		case "create":
			PostService.savePost(postContent, name);
			request.getRequestDispatcher("/home?name="+name).forward(request, response);
			break;
		}
		
		
	}

}
