package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import dto.UserPost;
import service.UserService;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HomeController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String name = request.getParameter("name");
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
		
		if(!user.getName().isEmpty()) {
			System.out.println("Hello user= " + user.getName());
		}
		
		if(user.isAdmin()) {
			request.getRequestDispatcher("pages/homeAdmin.jsp").forward(request, response);
		}else {
			//Logika za dohvatanje postova
			//UserPosts userPosts = UserService.getUserPosts(name);
			//get all friend posts
			List<UserPost> userPosts = UserService.getAllHomePostsForUser(user.getName());
			//merge - spajanje svih postova
			
			if(userPosts != null && userPosts.size() > 0 ) {
				request.setAttribute("userPosts", userPosts);
			}else {
				String NoPostMsg = "You have no POSTS!";
				request.setAttribute("NoPostMsg", NoPostMsg);
				
				//request.setAttribute("name", name);
				request.getRequestDispatcher("pages/home.jsp").forward(request, response);
			}
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
