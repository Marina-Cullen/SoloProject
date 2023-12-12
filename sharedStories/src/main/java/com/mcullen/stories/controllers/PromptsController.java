package com.mcullen.stories.controllers;

import java.util.List;

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

import com.mcullen.stories.models.Prompts;
import com.mcullen.stories.models.User;
import com.mcullen.stories.services.PromptsService;
import com.mcullen.stories.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PromptsController {
	@Autowired
	private PromptsService promptServ;
	@Autowired
	private UserService userServ;
	@Autowired
	private HttpSession session;

//CREATE A PROMPT PAGE
	@GetMapping("/create/prompt")
	public String newPromptPage(@ModelAttribute("newPrompt") Prompts newPrompt, Model viewModel, Model model) {
		Long userId = (Long) session.getAttribute("userId"); // Remember to to TYPECAST anything I get from session
		
		// If the user is NOT logged in, this will send them to the login page
		if (userId == null) {
			return "redirect:/";
		}
		//DROPDOWN OPTIONS
		String[] categories = { "Early Childhood", "Childhood", "Teen Years", "Early Adulthood", "Adulthood" };
		model.addAttribute("allCategories",categories); /* If you pass anything in via the Model, you must pass it in again!!! */

		User foundUserOrNull = userServ.getUserById(userId);
		viewModel.addAttribute("loggedUser", foundUserOrNull);
		return "promptForm.jsp";
	}

//CREATE A PROMPT=POST
	@PostMapping("/create/prompt")
	public String newPromptPost(@Valid @ModelAttribute("newPrompt") Prompts newPrompt, BindingResult result,
			Model model) {
		String[] categories = { "Early Childhood", "Childhood", "Teen Years", "Early Adulthood", "Adulthood" };
		model.addAttribute("allCategories",categories); /* If you pass anything in via the Model, you must pass it in again!!! */

		// If the validations are not good...
		if (result.hasErrors()) {
			return "promptForm.jsp";

		}
		// The validations are good
		promptServ.createprompt(newPrompt); // Talk to the service
		return "redirect:/prompts";
	}

//READ ALL PROMPTS
	@GetMapping("/prompts")
	public String allPrompts(Model model, Model viewModel) {
		Long userId = (Long) session.getAttribute("userId"); // Remember to to TYPECAST anything I get from session
		// If the user is NOT logged in, this will send them to the login page
		if (userId == null) {
			return "redirect:/";
		}
		User foundUserOrNull = userServ.getUserById(userId);
		viewModel.addAttribute("loggedUser", foundUserOrNull);
		List<Prompts> allPrompts = promptServ.readAllPrompts();
		model.addAttribute("allPrompts", allPrompts);
		return "allPromptsPage.jsp";
	}

//READ ONE PROMPT
	@GetMapping("/prompt/{id}")
	public String onePrompt(@PathVariable("id") Long id, Model viewModel, Model model) {
		Long userId = (Long) session.getAttribute("userId"); // Remember to to TYPECAST anything I get from session
		// If the user is NOT logged in, this will send them to the login page
		if (userId == null) {
			return "redirect:/";
		}
		User foundUserOrNull = userServ.getUserById(userId);
		viewModel.addAttribute("loggedUser", foundUserOrNull);
		Prompts thisPrompts = promptServ.readOnePrompt(id);
		model.addAttribute("thisPrompts", thisPrompts);
		return "onePrompt.jsp";
	}

//UPDATE PROMPT PAGE
	@GetMapping("/prompt/{id}/update")
	public String updatePromptPage(@PathVariable("id") Long id, Model model, Model viewModel) {
		Long userId = (Long) session.getAttribute("userId"); // Remember to to TYPECAST anything I get from session
		// If the user is NOT logged in, this will send them to the login page
		if (userId == null) {
			
			return "redirect:/";

		}
		String[] categories = { "Early Childhood", "Childhood", "Teen Years", "Early Adulthood", "Adulthood" };
		model.addAttribute("allCategories", categories);
		User foundUserOrNull = userServ.getUserById(userId);
		viewModel.addAttribute("loggedUser", foundUserOrNull);
		Prompts thisPrompts = promptServ.readOnePrompt(id);
		model.addAttribute("updatedPrompt", thisPrompts);

		return "updatePromptPage.jsp";
	}

//UPDATE PROMPT POST
	@PutMapping("/prompt/{id}/update")
	public String updatePromptPost(@PathVariable("id") Long Id,
			@Valid @ModelAttribute("updatedPrompt") Prompts updatedPrompt, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Pass in attributes from the model OTHER than the actual object we're editing,
			// such as the CATEGORIES I offered
			
			return "updatePromptForm.jsp";
		}
		String[] categories = { "Early Childhood", "Childhood", "Teen Years", "Early Adulthood", "Adulthood" };
		model.addAttribute("allCategories", categories);
		promptServ.updatePrompt(updatedPrompt); // Edit prompt in DB via Service and Repository
		return "redirect:/prompts"; // back to the Prompts page
	}

//DELETE
	@DeleteMapping("/prompt/{id}/delete")
	public String promptToDelete(@PathVariable("id") Long id) {
		promptServ.deletePrompt(id);
		return "redirect:/home";
	}
//END
}