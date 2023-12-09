package com.mcullen.stories.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "stories")
public class Story {
	// PRIMARY KEY
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

	// VARIOUS COLUMNS TO ADD TO TABLE

	// STORY
	@NotBlank (message = "Enter Story Here")
	@Column (columnDefinition="LONGTEXT")
	private String story;
	
//JOINING COLUMN*****************************************
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="prompts_id")
	private Prompts storyPrompt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="user_id")
	private User storyByUser;
	
//CONSTRUCTORS
	public Story() {
	}

	public Story(Long id, @NotBlank(message = "Enter Story Here") String story, Prompts storyPrompt, User storyByUser) {
		super();
		this.id = id;
		this.story = story;
		this.storyPrompt = storyPrompt;
		this.storyByUser = storyByUser;
	}
//GETTERS AND SETTERS

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

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public Prompts getStoryPrompt() {
		return storyPrompt;
	}

	public void setStoryPrompt(Prompts storyPrompt) {
		this.storyPrompt = storyPrompt;
	}

	public User getStoryByUser() {
		return storyByUser;
	}

	public void setStoryByUser(User storyByUser) {
		this.storyByUser = storyByUser;
	}
	
	
//end
}
