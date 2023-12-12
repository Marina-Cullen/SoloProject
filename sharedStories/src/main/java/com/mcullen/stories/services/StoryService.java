package com.mcullen.stories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcullen.stories.models.Story;
import com.mcullen.stories.repositories.StoryRepository;

@Service

public class StoryService {
	//import the StoryRepository
	@Autowired
	private StoryRepository storyRepo;
	
	//CREATE
	public Story createStory(Story newStory) {
		return storyRepo.save(newStory);
	}
	
	//READ ONE
	public Story readOneStory(Long id) {
		Optional<Story> storyOrNull = storyRepo.findById(id); //Attempts to find the story by ID if there is one
		// Ternary operator (one-line if statement - "condition ? valueIfTrue :
		// valueIfFalse")
		return storyOrNull.isPresent() ? storyOrNull.get() : null;
	}
	
	//READ ALL
	public List<Story> readAllStories() {
		return storyRepo.findAll();
	}
	
	//UPDATE
	public Story updateStory(Story updatedStory) {
		return storyRepo.save(updatedStory);
	}
	
	//DELETE
	public void deleteStory(Long id) {
		storyRepo.deleteById(id);
	}

}
