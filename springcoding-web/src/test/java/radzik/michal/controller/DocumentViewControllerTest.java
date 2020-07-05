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
@ContextConfiguration(classes = DocumentViewControllerTest.DocumentViewControllerTestConfiguration.class)
public class DocumentViewControllerTest {
	private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	@Autowired
	DocumentViewController documentViewController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(documentViewController).setViewResolvers(viewResolver()).build();
	}
	
	@Test
	public void showDocument() throws Exception {
		mockMvc.perform(get("/document_view").param("documentName", "")).andExpect(status().isOk())
		.andExpect(view().name("document_view"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/document_view.jsp"));
	}
	
	@Configuration
	public static class DocumentViewControllerTestConfiguration{
		@Bean
		public DocumentViewController documentViewController() {
			return new DocumentViewController();
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
