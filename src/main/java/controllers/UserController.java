package controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;


//signup POST -> kreiranje usera u sistemu
//login POST
//logout GET
//reset_passsword POST
//delete account POST


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public UserController() {
        super();
    }

    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("UserController GET method: action="+action);
		
		switch(action) {
		case "logout":
			logout(response);
			
		
		
		}
		
	}
	private void logout(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordRetype = request.getParameter("passwordRetype");
		String action = request.getParameter("action");
		String userType = request.getParameter("userType");
		
		boolean admin = false;
		
		System.out.println("userType= " + userType);
		
		if(userType!= null && userType.equalsIgnoreCase("admin")) {
			admin = true;
		}
		
		
		
		
		switch(action) {
			case "signup":
				// signup
				signup(request, response, email, name, password, passwordRetype, admin);
				break;
			case "login":
				login(request, response, email, password);
				break;
		}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String email, String password) throws IOException {
		
		String errMsg = "";
		
		boolean fieldsEmpty = true;
		
		if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
			errMsg += "All fields must be entered";
		}
		
		if(errMsg.length() == 0) {
			fieldsEmpty = false;
		}
		
		if(UserService.checkUser(email, password)) {
			try {
				User user = UserService.getUser(email);
				request.getSession().setAttribute("user",user);
				response.sendRedirect("home");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else {
			System.out.println("IN-VALID LOGIN");
		}
		
	}



	private void signup(HttpServletRequest request, HttpServletResponse response, String email,String name, String password,
			String passwordRetype, boolean admin) throws ServletException, IOException {
		
		String errMsg = "";
		boolean fieldsEmpty = true;
		
		if(email == null || email.isEmpty() || name == null || name.isEmpty()|| password == null || password.isEmpty() || passwordRetype == null || passwordRetype.isEmpty()) {
			errMsg = "All fields must be entered <br>";
		}
		
		if(errMsg.length() == 0) {
			fieldsEmpty = false;
		}
		
		if(!fieldsEmpty && !isValidEmail(email)) {
			errMsg += "Mail must be in valid fomat. <br>";
		}
		
		String passwordStatusMessage = isValidPassword(password);
		
		if(!fieldsEmpty && !passwordStatusMessage.equalsIgnoreCase("OK")) {
			errMsg += passwordStatusMessage;
		}
		
		if(!password.equals(passwordRetype)) {
			errMsg += "Password and re-type password must be equal. <br>";
		}
		
		
		
		if(errMsg.length() > 0) {
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
			
			
		}else {
			UserService.addUser(email, password, name, admin);
			User user;
			try {
				user = UserService.getUser(email);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("home");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	private String isValidPassword(String password) {
		boolean isValid = true;
		String statusMessage = "";
		
		if(password.length() > 20 || password.length() < 8) {
			statusMessage += "Password length must be between 8 and 20 characters. <br>";
			isValid = false;
		}
		
		String upperCaseChar = "(.*[A-Z].*)";
		if(!password.matches(upperCaseChar)) {
			statusMessage += "Password must have at least one uppercase character. <br>";
			isValid = false;
		}
		
		String lowerCaseChar = "(.*[a-z].*)";
		if(!password.matches(lowerCaseChar)) {
			statusMessage += "Password must have at least one lowercase character. <br>";
			isValid = false;
		}
		
		String specialCharacter = "(.*[@,#,$,%].*$)";
		if(!password.matches(specialCharacter)) {
			statusMessage += "Password must have at least one special character among [@#$%] <br>";
			isValid = false;
		}
		
		String numbers = "(.*[0-9].*)";
		if(!password.matches(numbers)) {
			statusMessage += "Password must have at least one number<br>";
			isValid = false;
		}
		
		if(isValid) {
			statusMessage = "OK";
		}
		
		return statusMessage;
	}

}
