package radzik.michal.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import radzik.michal.dto.UserDTO;
import radzik.michal.model.Document;
import radzik.michal.model.DocumentType;
import radzik.michal.model.User;
import radzik.michal.model.UserType;
import radzik.michal.service.DocumentService;
import radzik.michal.service.UserService;

@Controller
public class TabsController {

	private final DocumentService documentService;

	private final UserService userService;	
	
	@Autowired
	public TabsController(DocumentService documentService,UserService userService) {
		this.documentService = documentService;
        this.userService = userService;
        
    }

	@RequestMapping(value = "/tabs", method = RequestMethod.GET)
	public String createTabs(Model model) {
		List<UserType> userTypesValues = Arrays.asList(UserType.values());
		model.addAttribute("userTypesValues", userTypesValues);
		List<DocumentType> documentTypesValues = Arrays.asList(DocumentType.values());
		model.addAttribute("documentTypesValues", documentTypesValues);
		List<Document> documents = documentService.getAll();
		model.addAttribute("documents", documents);
		model.addAttribute("command", new Document());
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		model.addAttribute("userCommand", new UserDTO());
		return "tabs";
	}

	@RequestMapping(value = "/addDocumentAjax", method = RequestMethod.POST)
	public String addDocument(@ModelAttribute("command") Document document, Model model,
			@RequestParam(required = false) MultipartFile icoFile) {
		documentService.addDocument(document, icoFile);
		List<Document> documents = documentService.getAll();
		model.addAttribute("documents", documents);
		return "show_documents_simple";
	}

	@RequestMapping(value = "/addUserAjax", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("command") User user, Model model) {
		userService.addUser(user);
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "show_users_simple";
	}

	@RequestMapping(value = "/updateDocumentAjax", method = RequestMethod.POST)
	public String updateDocument(Document document, Model model,
			@RequestParam(required = false) MultipartFile icoFile) {
		documentService.updateDocument(document, icoFile);
		List<Document> documents = documentService.getAll();
		model.addAttribute("documents", documents);
		return "show_documents_simple";
	}

	@RequestMapping(value = "/deleteDocumentAjax/{documentId}", method = RequestMethod.POST)
	public String deleteDocument(@PathVariable Long documentId, Model model) {
		documentService.deleteDocument(documentId);
		List<Document> documents = documentService.getAll();
		model.addAttribute("documents", documents);
		return "show_documents_simple";
	}

	@RequestMapping(value = "/updateUserAjax", method = RequestMethod.POST)
	public String updateUser(UserDTO user, Model model, String birthDate) {
		userService.updateUser(user);
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "show_users_simple";
	}

	@RequestMapping(value = "/deleteUserAjax/{userId}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable Long userId, Model model) {
		userService.deleteUser(userId);
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "show_users_simple";
	}
}
