package com.mcullen.stories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcullen.stories.models.Prompts;
import com.mcullen.stories.models.Story;
import com.mcullen.stories.repositories.PromptsRepository;

@Service
public class PromptsService {

//imports the information from the PromptsRepository file
	@Autowired
	private PromptsRepository promptRepo;

	//CREATE
	public Prompts createprompt(Prompts newPrompt) {
		return promptRepo.save(newPrompt);
	}
	//READ ONE
	public Prompts readOnePrompt(Prompts id) {
		Optional<Prompts> promptOrNull = promptRepo.findById(id);//attempts to find the prompt by ID
		// Ternary operator (one-line if statement - "condition ? valueIfTrue :
		// valueIfFalse")
		return promptOrNull.isPresent() ? promptOrNull.get() : null;
	}
	// READ ALL
	public List<Prompts> readAllPrompts() {
		return promptRepo.findAll();
	}
	// UPDATE
	public Prompts updatePrompt(Prompts editedPrompt) {
		return promptRepo.save(editedPrompt);
	}
	// DELETE
	public void deletePrompt (Long id) {
		Prompts thisPrompts = this.readOnePrompt(id);//Grabs the Prompt first
		//Below will delete all stories linked to the prompt
		int numberOfStories = thisPrompts.getStories().size();
		for (int i = 0; i < numberOfStories; i++) {
			Story thisStory = thisPrompts.getStories().remove(i); //Removes story from the list
			storyRepo.delete(thisStory); //Removes the story from the DB via the story repo
		}
		promptRepo.deleteById(id); //All is deleted now
	}
	
	
	
	// end
}
