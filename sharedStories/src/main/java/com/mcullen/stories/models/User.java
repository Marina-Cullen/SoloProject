package com.mcullen.stories.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

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
		this.updatedAt = new Date();//This creates an updateAt upon creation
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
//VARIOUS COLUMNS TO ADD TO TABLE
	
	//USERNAME
	//FIRST NAME
	//LAST NAME
	//EMAIL
	//PASSWORD
	//DATE OF BIRTH
//JOINING COLUMNS
	
	
//end
}
