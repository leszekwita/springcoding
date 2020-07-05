package radzik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import radzik.michal.dto.UserDTO;
import radzik.michal.model.User;
import radzik.michal.service.UserService;

@Controller
public class LoginController {
	
	private final UserService userService;

	public LoginController(UserService userService) {
	        this.userService = userService;
	    }
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logIn(Model model) {
		model.addAttribute("command", new UserDTO());
		return "login";
	}

	@RequestMapping(value = "/loginExecute", method = RequestMethod.POST)
	public String logInExecute(User user, Model model) {

		boolean authentication = userService.loginExecute(user.getEmail(),
				user.getPassword());

		if (!authentication) {					
			return "redirect:/login";
		}

		return "index";
	}
}