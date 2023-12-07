package com.mcullen.stories.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//NO ANNOTATIONS NEEDED HERE...we are NOT saving any of this, so no need to save anything to the DB
public class LoginUser {
	//EMAIL
	@NotEmpty(message = "Email is required")
	@Email(message = "Please enter a valid email")
	private String email;
	
	//PASSWORD
	@NotEmpty(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	
	//CONSTRUCTOR
	public LoginUser() {}

	public LoginUser(
			@NotEmpty(message = "Email is required") @Email(message = "Please enter a valid email") String email,
			@NotEmpty(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String password) {
		super();
		this.email = email;
		this.password = password;
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
	
	
	
	
	
	
	
//END
}
