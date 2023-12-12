package com.mcullen.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mcullen.stories.models.Story;
import com.mcullen.stories.models.User;
import com.mcullen.stories.services.PromptsService;
import com.mcullen.stories.services.StoryService;
import com.mcullen.stories.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class StoryController {
	@Autowired
	private StoryService storyServ;
	@Autowired
	private PromptsService promptServ;
	@Autowired
	private UserService userServ;
	@Autowired
	private HttpSession session;
	
	//CREATE-GET
	
	//CREATE-POST
	@PostMapping ("/create/story")
	public String newStory(@Valid @ModelAttribute("newStory")Story newStory, BindingResult result, Model model) {
		// If the validations are not good...
		if (result.hasErrors()) {
			return "onePrompt.jsp";
		}
		// The validations are good
		storyServ.createStory(newStory); // Talk to the service
		return "redirect:/prompts";
	}
	//READ ONE STORY
	
	//READ ALL STORIES WITH PROMPT
	
	//UPDATE-GET
	@GetMapping ("/story/{id}/update")
	public String updateStory(@PathVariable("id")Long id, Model model, Model viewModel) {
		Long userId = (Long) session.getAttribute("userId"); // Remember to to TYPECAST anything I get from session
		// If the user is NOT logged in, this will send them to the login page
		if (userId == null) {
			return "redirect:/";
		}
		User foundUserOrNull = userServ.getUserById(userId);
		viewModel.addAttribute("loggedUser", foundUserOrNull);
		Story thisStory = storyServ.readOneStory(id);
		model.addAttribute("updatedStory", thisStory);
		return "storyUpdate.jsp";
	}
	
	//UPDATE-POST
	@PutMapping("/story/{id}/update")
	public String updateStoryPost(@PathVariable("id")Long id,@Valid @ModelAttribute("updatedStory")
	Story updatedStory, BindingResult result, Model model) {
		//VALIDATORS ARE RAN
		if (result.hasErrors()) {
			return "storyUpdate.jsp";// if there are errors, this page will render again with error messages
		}
		storyServ.updateStory(updatedStory); //Update will change in DB via Service and Repo
		return "redirect:/prompt/{id}"; //redirected back to original prompt
	}
	
	//DELETE STORY
	@DeleteMapping("/story/{id}/delete")
	public String deleteStory(@PathVariable("id")Long id) {
		storyServ.deleteStory(id);
		return "redirect:/prompts";
	}
	
	

}
