package radzik.michal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import radzik.michal.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserRegisteredControllerTest.UserRegisteredControllerTestConfiguration.class)
public class UserRegisteredControllerTest {

	
    private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	@Autowired
	UserRegisteredController userRegisteredController;
	
    private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userRegisteredController).setViewResolvers(viewResolver()).build();
	}
	@Test
	public void addUser() throws Exception {
		mockMvc.perform(post("/addUser"))
		.andExpect(view().name("user_registered"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/user_registered.jsp"))
		.andExpect(model().hasNoErrors())
		.andExpect(model().size(2))
		.andExpect(model().attributeDoesNotExist("persons2")).andReturn();
	}

	
	public static class UserRegisteredControllerTestConfiguration{
		
		@Bean
		public UserServiceImpl userService() {
			return Mockito.mock(UserServiceImpl.class);
		}
		
		@Bean
		public UserRegisteredController userRegisteredController() {
			return new UserRegisteredController(userService());
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
