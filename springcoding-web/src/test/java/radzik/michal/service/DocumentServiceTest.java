package radzik.michal.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.multipart.MultipartFile;

import radzik.michal.AppConfig;
import radzik.michal.dao.DocumentDAO;
import radzik.michal.model.Attachment;
import radzik.michal.model.Document;
import radzik.michal.model.DocumentType;
import radzik.michal.util.Utils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Utils.class })
public class DocumentServiceTest {

	@InjectMocks
	DocumentServiceImpl documentService;

	@Mock
	DocumentDAO documentDAO;

	@Mock
	AppConfig appConfig;

	@Mock
	MultipartFile icoFile;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		getDocuments();
		PowerMockito.spy(Utils.class);
		PowerMockito.doNothing().when(Utils.class, "writeFile", Mockito.any(), Mockito.any(), Mockito.any());
	}

	@Test
	public void getAllDocuments() throws Exception {

		// given
		Mockito.when(documentDAO.getAll()).thenReturn(getDocuments());

		// when
		List<Document> documents = documentService.getAll();

		// then
		verify(documentDAO, times(1)).getAll();
		assertThat(documents, hasSize(4));

	}

	@Test(expected = NullPointerException.class)
	public void ShouldThrowNullWhenDocumentsIsNullInGetAll() {

		// given
		List<Document> documents = null;
		Mockito.when(documentDAO.getAll()).thenReturn(documents);

		// when
		documentService.getAll();

	}

	@Test
	public void shouldReturnDocumentWhenUpdate() throws Exception {

		// given
		Document document = new Document();
		Mockito.when(documentDAO.updateDocument(document)).thenReturn(document);
		ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);
		// when
		Document documentUpdated = documentService.updateDocument(document, icoFile);

		// then
		verify(documentDAO).updateDocument(captor.capture());
		assertThat(documentUpdated, is(document));
	}


	@Test
	public void deleteDocument() {

		// given
		Document document = new Document();
		Mockito.when(documentDAO.get(document.getId())).thenReturn(document);
		ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);
		// when
		documentService.deleteDocument(document.getId());

		// then
		verify(documentDAO).deleteDocument(captor.capture());
		Mockito.verify(documentDAO, times(1)).deleteDocument(document);
		
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenDeleteDocumentIsNull() {
		
		// given
		Document document = null;
		
		Mockito.when(documentDAO.get(document.getId())).thenReturn(document);

		// when
		documentService.deleteDocument(document.getId());
	}

	@Test
	public void shouldReturnDocumentWhenAddDocument() {
		
		// given
		Document document = new Document();
		
		ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);
		
		Mockito.when(documentDAO.addDocument(document)).thenReturn(document);

		// when
		Document documentAdded = documentService.addDocument(document, icoFile);

		// then
		verify(documentDAO).addDocument(captor.capture());
		
		assertThat(documentAdded, is(document));
	}



	private List<Document> getDocuments() {
		List<Attachment> attachments = new ArrayList<Attachment>();

		List<Document> documents = new ArrayList<Document>();
		documents.add(new Document((long) 1,"XYZ", "1234", DocumentType.Agreement, new Date(), null, null));
		documents.add(new Document((long) 2,"MDB", "5678",  DocumentType.Invoice, new Date(), attachments, null));
		documents.add(new Document((long) 3,"PZM", "9112",  null, new Date(), attachments, null));
		documents.add(new Document((long) 4,"RWA", "7672", DocumentType.Proposition, new Date(), attachments, null));
		return documents;
	}
}
