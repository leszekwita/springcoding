package radzik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import radzik.michal.model.Document;
import radzik.michal.service.DocumentService;


@Controller
public class DocumentRegisteredController {

	
	private final DocumentService documentService;

	public DocumentRegisteredController(DocumentService documentService) {
	        this.documentService = documentService;
	    }


	@RequestMapping(value = "/addDocument", method = RequestMethod.POST)
	public String addDocument(Document document, Model model, @RequestParam(required = false) MultipartFile icoFile) {
		documentService.addDocument(document, icoFile);
		model.addAttribute("title", document.getTitle());
		return "document_registered";
	}
}
