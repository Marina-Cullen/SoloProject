package com.mcullen.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mcullen.stories.models.LoginUser;
import com.mcullen.stories.models.User;
import com.mcullen.stories.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired // Auto-injects the Service file
	private UserService userServ;

	@Autowired // Auto-injects session, so we don't have to keep coding into each of the
				// related routes
	
	private HttpSession session;

	//******ABOUT AND LOGIN PAGE/ Landing Page
	@GetMapping("/")
	public String loginpage(@ModelAttribute("newLogin") LoginUser newLogin) {
		return "landing.jsp";
	}
	//***SHOWS REGISTRATION FORM
	@GetMapping("/register")
	public String newRegister (@ModelAttribute("newUser") User newUser) {
		return "registration.jsp";
	}
	
	//***REGISTRATION PROCESS
	@PostMapping("/register")
	public String newUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model viewModel) {
		User newUserOrNull = userServ.validateRegistration(newUser, result);
		if (newUserOrNull == null) {
			viewModel.addAttribute("newLogin", new LoginUser());
			return "registration.jsp";
		}
		// ***SAVE THE ID FOR SESSION***
		session.setAttribute("userId", newUserOrNull.getId());
		return "redirect:/home";
	}

	//******LOGIN PROCESS
	@PostMapping("/loggingIn")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
			Model viewModel) {
		User loggedUserOrNull = userServ.validateLogin(newLogin, result);
		if (loggedUserOrNull == null) { // Logging in is unsuccessful
			viewModel.addAttribute("newUser", new User());
			return "loginReg.jsp";
		}
		// ******************SAVE the ID of the new user in SESSION*********************
		session.setAttribute("userId", loggedUserOrNull.getId());
		return "redirect:/home"; // make sure to REDIRECT because of POSTMAPPING
	}

	// HOME ROUTE = sometimes called the dashboard / Read All
	@GetMapping("/home")
	public String home(Model viewModel, Model model) {
		Long userId = (Long) session.getAttribute("userId"); // Remember to to TYPECAST anything I get from session
		// If the user is NOT logged in, this will send them to the login page
		if (userId == null) {
			return "redirect:/";
		}
		//if the User is logged in then they can see the desired page
		User foundUserOrNull = userServ.getUserById(userId);
		viewModel.addAttribute("loggedUser", foundUserOrNull);
		return "home.jsp";
	}

//LOGOUT ROUTE
	@PostMapping("/logout") // Note the POSTMAPPING
	public String logout() {
		session.invalidate(); // This will CLEAR THE SESSION at time of logout
		return "redirect:/";
	} 
}
