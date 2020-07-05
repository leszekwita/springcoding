package radzik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocumentViewController {

	@RequestMapping(value="/document_view", method= RequestMethod.GET)
	public String showDocument(Model model,
			@RequestParam("documentName") String documentName) {
		model.addAttribute("docName", documentName);
		return "document_view";
	}
}
