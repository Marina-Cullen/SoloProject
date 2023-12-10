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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "prompts")
public class Prompts {
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
		this.updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
//OTHER COLUMNS
	//TITLE
	@NotBlank (message = "This Field is Required")
	@Size (min = 2, max = 255, message = "TItle must be between 2-225 characters")
	private String title;
	
	//CATEGORY -DROPDOWN
	@NotBlank (message = "This Field is Required")
	private String category;
	
	//PROMPT COLUMN TEXTBOX
	@NotBlank (message = "This Field is Required")
	@Column (columnDefinition="TEXT")
	private String prompt;
	
//JOINING COLUMNS ***********************************	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn (name= "user_id")
	private User postingUser;
	
	@OneToMany(mappedBy="storyPrompt", fetch=FetchType.LAZY)
	private List<Story> stories;
	
//CONSTRUCTOR*****************************************
	public Prompts() {
	}

	public Prompts(Long id,
			@NotBlank(message = "This Field is Required") @Size(min = 2, max = 255, message = "TItle must be between 2-225 characters") String title,
			@NotBlank(message = "This Field is Required") String category,
			@NotBlank(message = "This Field is Required") String prompt, User postingUser, List<Story> stories) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.prompt = prompt;
		this.postingUser = postingUser;
		this.stories = stories;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public User getPostingUser() {
		return postingUser;
	}

	public void setPostingUser(User postingUser) {
		this.postingUser = postingUser;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

//GETTERS AND SETTERS************************************************

	
	
//end	
}
