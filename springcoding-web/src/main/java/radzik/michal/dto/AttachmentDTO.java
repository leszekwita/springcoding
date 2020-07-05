package radzik.michal.dto;

import radzik.michal.model.Attachment;

public class AttachmentDTO extends Attachment {

	private Integer attachmentNumber;

	public AttachmentDTO(Integer attachmentNumber) {
		this.attachmentNumber = attachmentNumber;
	}

	public AttachmentDTO(Attachment attachment) {
		this.setId(attachment.getId());
		this.setName(attachment.getName());
		this.setDocument(attachment.getDocument());

		String attachmentName = getName();
		String[] attachmentNameArr = attachmentName.split(" ");
		this.setAttachmentNumber(Integer.valueOf(attachmentNameArr[1]));
	}

	public Integer getAttachmentNumber() {
		return attachmentNumber;
	}

	public void setAttachmentNumber(Integer attachmentNumber) {
		this.attachmentNumber = attachmentNumber;
	}
}
