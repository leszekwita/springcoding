package radzik.michal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DocumentControllerTest.DocumentControllerTestConfiguration.class)
public class DocumentControllerTest {
	
	private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	@Autowired
	DocumentController documentController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(documentController).setViewResolvers(viewResolver()).build();
	}
	

	@Test
	public void createFormDocument() throws Exception {
		mockMvc.perform(get("/document/")).andExpect(status().isOk())
		.andExpect(view().name("document")).andExpect(forwardedUrl("/WEB-INF/jsp/document.jsp"));
	}
	
	@Test
	public void createFormDocumentSimple() throws Exception {
		mockMvc.perform(get("/document_simple")).andExpect(status().isOk())
		.andExpect(view().name("document_simple")).andExpect(forwardedUrl("/WEB-INF/jsp/document_simple.jsp"));
	}
	
	
	@Configuration
	public static class DocumentControllerTestConfiguration{
		@Bean
		public DocumentController documentController() {
			return new DocumentController();
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
