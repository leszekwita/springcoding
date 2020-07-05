package radzik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import radzik.michal.model.User;
import radzik.michal.service.UserService;

@Controller
public class UserRegisteredController {
	
	private final UserService userService;

	public UserRegisteredController(UserService userService) {
	        this.userService = userService;
	    }

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser( User user, Model model) {
		userService.addUser(user);
		model.addAttribute("userName", user.getEmail());
		return "user_registered";
	}
}
