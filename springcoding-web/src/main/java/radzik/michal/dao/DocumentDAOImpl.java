package radzik.michal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.model.Attachment;
import radzik.michal.model.Document;

@Repository
public class DocumentDAOImpl implements DocumentDAO {

	@PersistenceContext(unitName = "michal")
	private EntityManager em;

	@Override
	@Transactional
	public Document addDocument(Document document) {

		em.persist(document);
		em.flush();

		return document;
	}

	@Override
	@Transactional
	public List<Document> getAll() {
		Query query = em.createQuery("FROM Document document order by creationDate");
		List<Document> documents = query.getResultList();
		return documents;
	}

	@Override
	@Transactional
	public Document updateDocument(Document document) {

		Document documentOld = em.find(Document.class, document.getId());
		List<Attachment> attachementsOld = documentOld.getAttachments();

		for (Attachment attachment : attachementsOld) {
			em.remove(attachment);
		}

		em.merge(document);
		return document;
	}

	@Override
	@Transactional
	public void deleteDocument(Document document) {
		em.remove(document);

	}

	@Override
	public Document get(Long documentId) {
		return em.find(Document.class, documentId);
	}

}
