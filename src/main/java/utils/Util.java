package utils;

import java.util.ArrayList;
import java.util.List;

import domain.User;

public class Util {
	
	// public static Map<String, String> users = new HashMap();
	
	public static List<User> users = new ArrayList<>();
	
	
	public static void addUser(String email, String password, String name, boolean admin) {
		User user = new User(email, password, name, admin);
		users.add(user);
	}
	
	public static boolean checkUser(String email, String password) {
		
		
		
		for(User user : users ) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;

	}
	
	public static User getUser(String email) throws Exception {
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		
		throw new Exception("No user with email=" + email);
	}
}
	