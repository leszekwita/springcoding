package radzik.michal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import radzik.michal.service.DocumentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShowDocumentsControllerTest.ShowDocumentsControllerTestConfiguration.class)
public class ShowDocumentsControllerTest {
	
	private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	@Autowired
	ShowDocumentsController showDocumentsController;
	
    private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(showDocumentsController).setViewResolvers(viewResolver()).build();
	}
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/show_documents")).andExpect(status().isOk())
		.andExpect(view().name("show_documents"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_documents.jsp"));
	}
	
	@Configuration
	public static class ShowDocumentsControllerTestConfiguration{
	
		@Bean
		public DocumentServiceImpl documentService() {
			return Mockito.mock(DocumentServiceImpl.class);
		}
		
		@Bean
		public ShowDocumentsController showDocumentsController() {
			return new ShowDocumentsController(documentService());
		}
	}
	
	private ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		return viewResolver;
	}
}
