package radzik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String getHome() {
		return "index";
	}

	@RequestMapping("index")
	public String getIndex() {
		return "index";
	}

	@RequestMapping("test")
	public String getTest() {
		return "index";
	}
}
