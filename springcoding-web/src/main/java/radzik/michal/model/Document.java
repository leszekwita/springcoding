package radzik.michal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "document")
public class Document {

	@Id
	@SequenceGenerator(name = "DocumentSequence", sequenceName = "document_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocumentSequence")
	private Long id;

	private String title;

	private String number;

	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	private Date creationDate;

	@OneToMany(mappedBy = "document", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private List<Attachment> attachments = new ArrayList<Attachment>();

	@Transient
	private List<String> attachmentsTexts = new ArrayList<String>();

	public Document() {
	}

	public Document(Long id, String title, String number, DocumentType documentType, Date creationDate,
			List<Attachment> attachments, List<String> attachmentsTexts) {
		super();
		this.id = id;
		this.title = title;
		this.number = number;
		this.documentType = documentType;
		this.creationDate = creationDate;
		this.attachments = attachments;
		this.attachmentsTexts = attachmentsTexts;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@PrePersist
	public void setDateDefault() {
		if (creationDate == null) {
			setCreationDate(new Date());
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<String> getAttachmentsTexts() {
		return attachmentsTexts;
	}

	public void setAttachmentsTexts(List<String> attachmentsTexts) {
		this.attachmentsTexts = attachmentsTexts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + ((attachmentsTexts == null) ? 0 : attachmentsTexts.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (attachmentsTexts == null) {
			if (other.attachmentsTexts != null)
				return false;
		} else if (!attachmentsTexts.equals(other.attachmentsTexts))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (documentType != other.documentType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
