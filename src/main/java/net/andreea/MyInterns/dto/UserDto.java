package net.andreea.MyInterns.dto;

import java.io.Serializable;

public class UserDto implements Serializable{

	private long id;
	private String username;
	private String email;
	private String password;
	private String isMentor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getIsMentor() {
		return isMentor;
	}

	public void setIsMentor(String isMentor) {
		this.isMentor = isMentor;
	}
}
