package service;

import dao.Dao;
import domain.User;

public class PostService {

	static Dao dao = new Dao();
	
	public static void savePost(String post, String name) {
		User user = dao.getUserByName(name);
		dao.savePost(post, user.getId());
	}
	
	public static void deletePost(int postId) {
		dao.deletePost(postId);
	}
	
}
