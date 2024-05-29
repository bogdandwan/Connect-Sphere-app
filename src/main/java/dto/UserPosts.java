package dto;

import java.util.List;

public class UserPosts {
	
	String name;
	List<String> posts;
	
	
	public UserPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPosts(String name, List<String> posts) {
		super();
		this.name = name;
		this.posts = posts;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<String> getPosts() {
		return posts;
	}


	public void setPosts(List<String> posts) {
		this.posts = posts;
	}
	
	

}
