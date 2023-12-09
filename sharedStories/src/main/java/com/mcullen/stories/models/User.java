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
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	// CONFIRM PASSWORD
	@Transient // This annotation means that this column will not be saved in the DB
	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, message = "Confirm Password must be at least 8 characters long")
	private String confirm;

	// DATE OF BIRTH
	@NotNull(message = "Date of Birth is Required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	
//CONSTRUCTOR**********************************************
	public User() {
	}
	public User(Long id,
			@NotEmpty(message = "Please enter a valid Username") @Size(min = 3, max = 30, message = "Username must be between 3 - 30 characters") String userName,
			@NotEmpty(message = "Kindly enter your first name") @Size(min = 2, max = 40, message = "First name requires 2-40") String firstName,
			@NotEmpty(message = "Kindly enter your last name") @Size(min = 2, max = 40, message = "Last name requires 2-40") String lastName,
			@NotEmpty(message = "Email is required") @Email(message = "Please enter a valid email") String email,
			@NotEmpty(message = "Password is required") @Size(min = 8, max = 50, message = "Password must be between 8 - 50 characters long") String password,
			@NotEmpty(message = "Confirm Password is required!") @Size(min = 8, max = 50, message = "Confirm Password must be between 8 and 50 characters") String confirm,
			@NotNull(message = "Date of Birth is Required") Date dob,
			@NotBlank(message = "Please leave a quote") String quote, List<Prompts> prompts, List<Story> stories) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.dob = dob;
		this.quote = quote;
		this.prompts = prompts;
		this.stories = stories;
	}
	
//GETTERS AND SETTERS*******************************************
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public List<Prompts> getPrompts() {
		return prompts;
	}
	public void setPrompts(List<Prompts> prompts) {
		this.prompts = prompts;
	}
	public List<Story> getStories() {
		return stories;
	}
	public void setStories(List<Story> stories) {
		this.stories = stories;
	}
	
	
//end
}
