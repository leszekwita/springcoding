package radzik.michal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import radzik.michal.model.Document;
import radzik.michal.service.DocumentService;


@Controller
public class ShowDocumentsController {

	private final DocumentService documentService;	

	public ShowDocumentsController(DocumentService documentService) {
	        this.documentService = documentService;
	    }
	
	@RequestMapping(value = "/show_documents", method = RequestMethod.GET)
	public String getAll(Model model) {
		List<Document> documents = documentService.getAll();
		model.addAttribute("documents", documents);
		return "show_documents";

	}
}
