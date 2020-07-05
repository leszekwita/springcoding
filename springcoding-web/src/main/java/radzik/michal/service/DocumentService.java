package radzik.michal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import radzik.michal.model.Document;

public interface DocumentService {
	
	public Document addDocument(Document document, MultipartFile icoFile);

	public List<Document> getAll();

	public Document updateDocument(Document document, MultipartFile icoFile);

	public void deleteDocument(Long documentId);
 
}
