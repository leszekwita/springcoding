package radzik.michal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import radzik.michal.service.DocumentServiceImpl;
import radzik.michal.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TabsControllerTest.TabsControllerTestConfiguration.class)
public class TabsControllerTest {
	
    private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	@Autowired
	TabsController tabsController;
	
    private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(tabsController).setViewResolvers(viewResolver()).build();
	}
	
	@Test
	public void createTabs() throws Exception {
		mockMvc.perform(get("/tabs")).andExpect(status().isOk())
		.andExpect(view().name("tabs"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/tabs.jsp"));
	}
	
	@Test
	public void addDocument() throws Exception {
		mockMvc.perform(post("/addDocumentAjax"))
		.andExpect(view().name("show_documents_simple"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_documents_simple.jsp")).andExpect(model().hasNoErrors())
		.andExpect(model().size(2)).andExpect(model().attributeExists("documents"))
		.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}
	
	@Test
	public void addUser() throws Exception {
		mockMvc.perform(post("/addUserAjax"))
		.andExpect(view().name("show_users_simple"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_users_simple.jsp")).andExpect(model().hasNoErrors())
		.andExpect(model().size(2)).andExpect(model().attributeExists("users"))
		.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}
	
	@Test
	public void updateDocument() throws Exception {
		mockMvc.perform(post("/updateDocumentAjax"))
		.andExpect(view().name("show_documents_simple"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_documents_simple.jsp")).andExpect(model().hasNoErrors())
		.andExpect(model().size(2)).andExpect(model().attributeExists("documents"))
		.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}
	
	@Test
	public void deleteDocument() throws Exception {
		mockMvc.perform(post("/deleteDocumentAjax/{documentId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("show_documents_simple"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_documents_simple.jsp")).andReturn();
	}
	
	@Test
	public void updateUser() throws Exception {
		mockMvc.perform(post("/updateUserAjax"))
		.andExpect(status().isOk())
		.andExpect(view().name("show_users_simple"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_users_simple.jsp"))
		.andExpect(model().size(2)).andExpect(model().attributeExists("users"))
		.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}
	
	@Test
	public void deleteUser() throws Exception {
		mockMvc.perform(post("/deleteUserAjax/{userId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("show_users_simple"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/show_users_simple.jsp"))
		.andExpect(model().size(1)).andExpect(model().attributeExists("users"))
		.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}
	
	public static class TabsControllerTestConfiguration{
		
		@Bean
		public DocumentServiceImpl documentService() {
			return Mockito.mock(DocumentServiceImpl.class);
		}
		
		@Bean
		public UserServiceImpl userService() {
			return Mockito.mock(UserServiceImpl.class);
		}

		@Bean
		public TabsController tabsController() {
			return new TabsController(documentService(),userService());
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
