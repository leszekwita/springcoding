package radzik.michal.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import radzik.michal.dao.AttachmentDAO;
import radzik.michal.model.Attachment;
import radzik.michal.model.Document;

@RunWith(MockitoJUnitRunner.class)
public class AttachmentServiceTest {
	
	@InjectMocks
	private AttachmentServiceImpl attachmentServiceImpl;
	
	@Mock
	private AttachmentDAO attachmentDAO;
	

	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnAttachmentWhenCreateAttachment(){
		
		//given
		Attachment attachment = new Attachment((long) (1),"", new Document());
		Mockito.when(attachmentDAO.createAttachment(attachment)).thenReturn(attachment);
		ArgumentCaptor<Attachment> captor = ArgumentCaptor.forClass(Attachment.class);
		
		//when
		Attachment attachmentCreated = attachmentServiceImpl.createAttachment(attachment);
		
		//then
		verify(attachmentDAO).createAttachment(captor.capture());
		assertThat(attachmentCreated, is(attachment));
		
	}
	
	@Test
	public void shouldThrowExceptionWhenAttachmentIsNullInCreateAttachment(){
		
		//given
        Attachment attachment = null;
		
		Mockito.when(attachmentDAO.createAttachment(attachment)).thenReturn(attachment);
		
		//when
	    Attachment attachmentCreated = attachmentServiceImpl.createAttachment(attachment);
	    
	    //then
	    assertThat(attachmentCreated,is(nullValue()));
		
	}
}
