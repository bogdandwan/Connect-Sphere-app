package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.User;
import dto.UserPost;
import dto.UserPosts;



public class Dao {
	
	
      private DataSource ds;
      
      private final String INSERT_USER = "INSERT INTO `users` (`id`, `email`, `password`, `name`, `admin`) VALUES(NULL, ?, ?, ?, ?)";
      private final String SELECT_USER_BY_EMAIL = "SELECT * FROM `users` WHERE email = ?";
      private final String SELECT_USER_BY_NAME = "SELECT * FROM `users` WHERE name = ?";
      private final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM `users` WHERE email=? AND password=?";
      private final String SELECT_ALL_USER_POSTS = "SELECT u.name,p.content FROM `users` as u INNER JOIN `posts` as p ON u.id = p.user_id WHERE u.name=?";
      private final String SELECT_ALL_HOME_POSTS_FOR_USER = "SELECT DISTINCT u.id as userId, p.id as postId, u.name,p.content FROM `friends` as f INNER JOIN `users` as u ON f.friend_id = u.id INNER JOIN `posts` as p ON p.user_id = u.id WHERE f.user_id = ? OR p.user_id = ?";
      private final String SAVE_POST = "INSERT INTO `posts` (`id`, `content`, `user_id`) VALUES (NULL, ?, ?)";
      private final String DELETE_POST_BY_ID = "DELETE FROM `posts` WHERE id=?";
      
      
	public Dao(){
		
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	
	public void addUser(User user) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		
			try {
				con = ds.getConnection();
				pstm = con.prepareStatement(INSERT_USER);
				
			
				pstm.setString(1, user.getEmail());
				pstm.setString(2, user.getPassword());
				pstm.setString(3, user.getName());
				pstm.setBoolean(4, user.isAdmin());
				
				pstm.execute();
				
			
				con.close();
			
			}catch (SQLException e) {
			e.printStackTrace();
		
		}
	}
	
	public User getUserByEmail(String email) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USER_BY_EMAIL);
			pstm.setString(1, email);
			
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()){
				user= new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAdmin(rs.getBoolean("admin"));
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
		
		
	}
	
	public boolean checkIfUserExist(String email, String password) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
			pstm.setString(1, email);
			pstm.setString(2, password);
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			
			while(rs.next()){
				return true;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public UserPosts getUserPosts(String name) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserPosts userPosts = null;
		List<String> posts = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ALL_USER_POSTS);
			pstm.setString(1, name);
			pstm.execute();
			
			
			rs = pstm.getResultSet();
			userPosts = new UserPosts();
			userPosts.setName(name);
			posts = new ArrayList<String>();
			
			
			while(rs.next()) {
				
				posts.add(rs.getString("content"));
				
			}
			
			userPosts.setPosts(posts);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userPosts;
	}
	
	public User getUserByName(String name) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USER_BY_NAME);
			pstm.setString(1, name);
			
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()){
				
				user= new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAdmin(rs.getBoolean("admin"));
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
		
	}
	
	public List<UserPost> getAllHomePostsForUser(int id) {
		System.out.println("getAllHomePostsForUser - id = "+id);
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<UserPost> userPosts = new ArrayList<UserPost>();
		UserPost userPost = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ALL_HOME_POSTS_FOR_USER);
			pstm.setInt(1, id);
			pstm.setInt(2, id);
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()) {
				userPost = new UserPost(rs.getInt("postId"),rs.getInt("userId"),rs.getString("name"), rs.getString("content"));
				userPosts.add(userPost);
				
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userPosts;
	}
	
	public void savePost(String content, int userid) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SAVE_POST);
			pstm.setString(1, content);
			pstm.setInt(2, userid);
			pstm.execute();
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePost(int postId) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETE_POST_BY_ID);
			pstm.setInt(1, postId);
			pstm.execute();
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
