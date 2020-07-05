package radzik.michal.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.model.Attachment;

@Repository
public class AttachmentDAOImpl implements AttachmentDAO {

	@PersistenceContext(unitName = "michal")
	private EntityManager em;

	@Override
	@Transactional
	public Attachment createAttachment(Attachment attachment) {
		em.persist(attachment);
		return attachment;
	}

}
