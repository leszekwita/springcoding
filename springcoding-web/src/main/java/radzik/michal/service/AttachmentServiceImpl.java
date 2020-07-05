package radzik.michal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.dao.AttachmentDAO;

import radzik.michal.model.Attachment;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	private AttachmentDAO attachmentDAO;
	
	  public AttachmentServiceImpl(AttachmentDAO attachmentDAO) {
	  this.attachmentDAO = attachmentDAO; 
	  }
	 

	@Override
	@Transactional
	public Attachment createAttachment(Attachment attachment) {
		return attachmentDAO.createAttachment(attachment);
	}

}
