package radzik.michal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import radzik.michal.model.User;
import radzik.michal.service.UserService;

@Controller
public class ShowsUsersController {
	
	
	private final UserService userService;	

	public ShowsUsersController(UserService userService) {
	        this.userService = userService;
	    }

	@RequestMapping(value = "/show_users", method = RequestMethod.GET)
	public String getAll(Model model) {
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "show_users";
	}
}
