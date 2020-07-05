package radzik.michal.dao;

import java.util.List;

import radzik.michal.model.Document;

public interface DocumentDAO {
	
	public Document addDocument(Document document);

	public List<Document> getAll();

	public Document updateDocument(Document document);

	public void deleteDocument(Document document);

	Document get(Long documentId);
}

