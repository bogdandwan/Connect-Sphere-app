package service;


import java.util.List;

import dao.Dao;
import domain.User;
import dto.UserPost;
import dto.UserPosts;

public class UserService {
	
	static Dao dao = new Dao();
	
	public static void addUser(String email, String password, String name, boolean admin) {
		//Util.addUser(email, password, name); -- cuvanje podataka u lokalnom kesu
		
		
		//cuvanje podataka u bazi
		//Dao dao = new Dao();
		User user = new User(email, password, name, admin);
		dao.addUser(user);
		
	}
	
	public static boolean checkUser(String email, String password) {
		
		//Dao dao = new Dao();
		
		return dao.checkIfUserExist(email, password); 
	}
	
	public static User getUser(String email) throws Exception {
		//return Util.getUser(email);
		//Dao dao = new Dao();
		return dao.getUserByEmail(email);
		
	}
	
	public static UserPosts getUserPosts(String name) {
		
		//Dao dao = new Dao();
		return dao.getUserPosts(name);
		
	}
	
	public static List<UserPost> getAllHomePostsForUser(String name) {
		User user = dao.getUserByName(name);
		List<UserPost> userPosts = dao.getAllHomePostsForUser(user.getId());
		
		return userPosts;
	}
	
	

}
