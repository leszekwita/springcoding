package radzik.michal.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import radzik.michal.AppConfig;
import radzik.michal.dao.DocumentDAO;
import radzik.michal.dto.DocumentDTO;
import radzik.michal.model.Attachment;
import radzik.michal.model.Document;
import radzik.michal.util.Utils;

@Service
public class DocumentServiceImpl implements DocumentService {

	private DocumentDAO documentDAO;

	private AppConfig appConfig;

	public DocumentServiceImpl(DocumentDAO documentDAO, AppConfig appConfig) {
		this.documentDAO = documentDAO;
		this.appConfig = appConfig;
	}

	@Override
	@Transactional
	public Document addDocument(Document document, MultipartFile icoFile) {

		setAttachmentsInDocument(document);
		documentDAO.addDocument(document);
		Long id = document.getId();
		writeFile(icoFile, id);
		return document;
	}

	@Override
	@Transactional
	public List<Document> getAll() {
		List<Document> documents = documentDAO.getAll();
		List<Document> documentDtos = convertDocumentsToDocumentsDTOs(documents);
		return documentDtos;
	}

	@Override
	@Transactional
	public Document updateDocument(Document document, MultipartFile icoFile) {

		setAttachmentsInDocument(document);
		Long id = document.getId();
		if (icoFile == null) {
			return documentDAO.updateDocument(document);
		}
		writeFile(icoFile, id);
		return documentDAO.updateDocument(document);
	}

	@Override
	@Transactional
	public void deleteDocument(Long documentId) {

		String path = setPath(documentId);
		Document document = this.documentDAO.get(documentId);
		this.documentDAO.deleteDocument(document);
		File dir = new File(path);
		if (dir.exists()) {
			dir.delete();
		}
	}

	private String setPath(Long documentId) {
		
		String path = appConfig.getImagesDir() + appConfig.getImagesPath();
		String fileName = "ico_" + documentId;
		path += File.separator + fileName;
		return path;
	}

	private void setAttachmentsInDocument(Document document) {
		
		List<String> attachmentsTexts = document.getAttachmentsTexts();
		for (String attachmentText : attachmentsTexts) {

			Attachment attachment = new Attachment();
			attachment.setName(attachmentText);
			document.getAttachments().add(attachment);
			attachment.setDocument(document);
		}
	}

	private void writeFile(MultipartFile icoFile, Long id) {
		
		String path = appConfig.getImagesDir() + appConfig.getImagesPath();
		File icoDir = new File(path);
		String fileName = "ico_" + id;
		try {
			Utils.writeFile(icoFile, fileName, icoDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Document> convertDocumentsToDocumentsDTOs(List<Document> documents) {
		
		List<Document> documentDtos = new ArrayList<Document>();
		for (Document document : documents) {
			Date date = new Date();
			long dateTime = date.getTime();
			String path = appConfig.getServerLocation() + appConfig.getServerPath() + "/ico_" + document.getId()
					+ "?timestamp=" + dateTime;
			DocumentDTO documentDto = new DocumentDTO(document);
			documentDto.setIconPath(path);

			documentDtos.add(documentDto);
		}
		return documentDtos;
	}
}