package domain;

public class User {
	
	private int id;
	private String email;
	private String password;
	private String name;
	private boolean admin;
	
	
	public User(String email, String password, String name, boolean admin) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.admin = admin;
	}


	public User() {
		super();
	}

	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", admin=" +admin+ "]";
	}
	
	
	
}
