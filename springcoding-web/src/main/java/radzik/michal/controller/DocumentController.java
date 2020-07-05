package radzik.michal.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import radzik.michal.model.Document;
import radzik.michal.model.DocumentType;


@Controller
public class DocumentController {
	
	@RequestMapping(value="/document", method = RequestMethod.GET)
	public String createFormDocument(Model model) {
		model.addAttribute("command", new Document());
		return "document";
	}
	
	@RequestMapping(value="/document_simple", method = RequestMethod.GET)
	public String createFormDocumentSimple(Model model) {
		model.addAttribute("command", new Document());
		return "document_simple";
	}	
	
	
	@ModelAttribute("documentTypesValues")
	public List<DocumentType> getDocumentTypesValues(final HttpServletRequest request) {
		List<DocumentType> documentTypesValues = Arrays.asList(DocumentType.values());
		return documentTypesValues;
	}

}
