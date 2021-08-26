package com.fabbi.news.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class SignupDTO {
	
	@NotBlank(message = "Username is mandatory")
	private String username;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	private Set<String> role;

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
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
