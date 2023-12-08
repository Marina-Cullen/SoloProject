package com.mcullen.stories.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")

public class User {
//PRIMARY KEY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();// This creates an updateAt upon creation
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

//VARIOUS COLUMNS TO ADD TO TABLE

	// USERNAME --PUBLIC
	@NotEmpty(message = "Please enter a valid Username")
	@Size(min = 3, max = 30, message = "Username must be between 3 - 30 characters")
	private String userName;

	// FIRST NAME
	@NotEmpty(message = "Kindly enter your first name")
	@Size(min = 2, max = 40, message = "First name requires 2-40")
	private String firstName;

	// LAST NAME
	@NotEmpty(message = "Kindly enter your last name")
	@Size(min = 2, max = 40, message = "Last name requires 2-40")
	private String lastName;

	// EMAIL
	@NotEmpty(message = "Email is required")
	@Email(message = "Please enter a valid email")
	private String email;

	// PASSWORD
	@NotEmpty(message = "Password is required")
	@Size(min = 8, max = 50, message = "Password must be between 8 - 50 characters long")
	private String password;

	// CONFIRM PASSWORD
	@Transient // This annotation means that this column will not be saved in the DB
	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirm;

	// DATE OF BIRTH
	@NotNull(message = "Date of Birth is Required")
	@DateTimeFormat (pattern = "MM/dd/yyyy")
	private Date dob;
	
	//QUOTE
	@NotBlank (message = "Please leave a quote")
	@Column (columnDefinition = "TEXT")
	private String quote;
	
	
//JOINING COLUMNS *****************************************
	@OneToMany(mappedBy = "postingUser", fetch=FetchType.LAZY)
	private List<Prompts> prompts;
	
	@OneToMany(mappedBy = "storyByUser", fetch=FetchType.LAZY)
	private List<Story> stories;
//end
}
