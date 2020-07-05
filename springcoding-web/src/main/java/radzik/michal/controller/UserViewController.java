package radzik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

	@RequestMapping(value = "/user_view",method = RequestMethod.GET)
	public String showUser(Model model,@RequestParam("userName") String userName) {
		model.addAttribute("usName", userName);
		return "user_view";
	}
}
