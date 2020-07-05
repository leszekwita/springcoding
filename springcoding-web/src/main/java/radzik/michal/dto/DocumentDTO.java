package radzik.michal.dto;

import java.util.ArrayList;
import java.util.List;


import radzik.michal.model.Attachment;
import radzik.michal.model.Document;

public class DocumentDTO extends Document {

	private String iconPath;

	public DocumentDTO(Document document) {
		this.setId(document.getId());
		this.setNumber(document.getNumber());
		this.setTitle(document.getTitle());
		this.setDocumentType(document.getDocumentType());

		List<Attachment> attachments = document.getAttachments();
		List<Attachment> attachmentsDtos = new ArrayList<Attachment>();

		Attachment emptyAttachment1 = new AttachmentDTO(1);
		Attachment emptyAttachment2 = new AttachmentDTO(2);
		Attachment emptyAttachment3 = new AttachmentDTO(3);

		attachmentsDtos.add(emptyAttachment1);
		attachmentsDtos.add(emptyAttachment2);
		attachmentsDtos.add(emptyAttachment3);

		if (attachments != null) {
			for (Attachment attachment : attachments) {
				AttachmentDTO attachmentDTO = new AttachmentDTO(attachment);
				attachmentsDtos.set(attachmentDTO.getAttachmentNumber() - 1, attachmentDTO);

			}
			setAttachments(attachmentsDtos);
		}
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
}
