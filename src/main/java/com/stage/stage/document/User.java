package com.stage.stage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

	@Id
	private String id ;
	private String username ;
	private String password ;
	private boolean active ;
	private Role role ;
	
	
	public User() {
		
	
	}
	
	public User( String username, String password, boolean active, Role role) {
		
		this.username = username;
		this.password = password;
		this.active = active;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
