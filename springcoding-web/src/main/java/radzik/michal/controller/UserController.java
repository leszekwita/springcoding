package radzik.michal.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import radzik.michal.dto.UserDTO;
import radzik.michal.model.Document;
import radzik.michal.model.UserType;

@Controller
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String createFormUser(Model model) {
		model.addAttribute("command", new UserDTO());
		return "user";
	}

	@RequestMapping(value = "/user_simple", method = RequestMethod.GET)
	public String createFormUserSimple(Model model) {
		model.addAttribute("userCommand", new Document());
		return "user_simple";
	}

	@ModelAttribute("userTypesValues")
	public List<UserType> getUserTypesValues(final HttpServletRequest request) {
		List<UserType> userTypesValues = Arrays.asList(UserType.values());
		return userTypesValues;
	}
}
