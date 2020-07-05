package radzik.michal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import radzik.michal.model.Document;
import radzik.michal.model.DocumentType;
import radzik.michal.service.DocumentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DocumentRegisteredControllerTest.DocumentRegisteredControllerTestConfiguration.class)
public class DocumentRegisteredControllerTest {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	@Autowired
	private DocumentRegisteredController documentRegisteredController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(documentRegisteredController).setViewResolvers(viewResolver())
				.build();

	}

	@Test
	public void addDocument() throws Exception {
		Document document = new Document((long) 1, "XYZ", "1234", DocumentType.Agreement, new Date(), null, null);

		mockMvc.perform(post("/addDocument").param("title", document.getTitle()))
				.andExpect(view().name("document_registered"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/document_registered.jsp")).andExpect(model().hasNoErrors())
				.andExpect(model().size(2)).andExpect(model().attributeExists("title"))
				.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}

	private ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		return viewResolver;
	}

	@Configuration
	public static class DocumentRegisteredControllerTestConfiguration {
	

		@Bean
		public DocumentServiceImpl documentService() {
			return Mockito.mock(DocumentServiceImpl.class);
		}
		
		@Bean
		public DocumentRegisteredController documentRegisteredController() {
			return new DocumentRegisteredController(documentService());
		}
	}
}





