package com.mcullen.stories.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mcullen.stories.models.LoginUser;
import com.mcullen.stories.models.User;
import com.mcullen.stories.repositories.UserRepository;

@Service

public class UserService {

	@Autowired
	private UserRepository userRepo;

	// ADDITIONAL VALIDATIONS WILL GO HERE

//REGISTRATION
	public User validateRegistration(User newUser, BindingResult result) {

		// MATCHES THE PASSWORDS
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "passwordsDisagree.newUser.password", "Passwords do not agree");
		}
		// *****IF EMAIL IS ALREADY IN DATABASE
		Optional<User> possibleUser = userRepo.findByEmail(newUser.getEmail());
		if (possibleUser.isPresent()) { // If the email is found, you cannot register
			result.rejectValue("email", "emailTaken.registerUser.email", "This email is already registered.");

		}

		if (result.hasErrors()) {
			return null;
		}
		// IF all the VALIDATIONS are good...
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()); // THIS HASHES THE PASSWORD
		newUser.setPassword(hashedPassword);
		return userRepo.save(newUser);
	}

////LOGIN
	public User validateLogin(LoginUser newLogin, BindingResult result) {

		// ************SERCHES DB FOR EMAIL
		Optional<User> possibleUser = userRepo.findByEmail(newLogin.getEmail());
		if (possibleUser.isEmpty()) { // If no user found, no need to check the password
			result.rejectValue("email", "invalidCredentials.newLogin.email", "Invalid login credentials");
			return null; // Do not forget to add the return null to divide the actions
		}
		User thisUser = possibleUser.get(); // WE DO HAVE A USER HERE
		// CHECK the password from the form vs. the password from the database..... make
		// sure they match

		// ***********SEARCHES DB FOR MATCHING PASSWORD
		if (!BCrypt.checkpw(newLogin.getPassword(), thisUser.getPassword())) {
			result.rejectValue("email", "invalidCredentials.newLogin.email", "Invalid login credentials");
			return null;
		}

		// ***********SEARCHES FOR ANY FORM ERRORS
		if (result.hasErrors()) { // Any VALIDATION ERRORS
			return null;
		}
		return thisUser;
	}

//
//	// GET USER BY ID ....READ ONE
	public User getUserById(Long id) {
		Optional<User> possibleUser = userRepo.findById(id);
		return possibleUser.isPresent() ? possibleUser.get() : null;
	}
}
