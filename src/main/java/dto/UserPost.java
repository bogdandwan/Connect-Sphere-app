package dto;

public class UserPost {
	
	
	int userId;
	int postId;
	private String name;
	private String post;
	
	
	public UserPost(int userId, int postId,String name, String post) {
		super();
		this.name = name;
		this.post = post;
		this.userId = userId;
		this.postId = postId;
	}
	
	


	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	public int getPostId() {
		return postId;
	}




	public void setPostId(int postId) {
		this.postId = postId;
	}




	public UserPost() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}
	
	

}
