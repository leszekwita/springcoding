package radzik.michal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import radzik.michal.model.User;
import radzik.michal.model.UserType;
import radzik.michal.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LoginControllerTest.LoginControllerTestConfiguration.class)
public class LoginControllerTest {
	
	private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	@Autowired
	private LoginController loginController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver()).build();
	}
	
	
	@Test
	public void logIn() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
	}
	
	@Test
	public void logInExecute() throws Exception {
		User user1 = new User((long)1,"Tomasz","Janowicki","janowiki@wp.pl","1234",new Date(),true,null,UserType.Admin,null,null);
		mockMvc.perform(post("/loginExecute").param("title", user1.getEmail()))
		.andExpect(view().name("redirect:/login"))
		.andExpect(model().size(1)).andExpect(model().attributeDoesNotExist("persons2")).andReturn();;
	}
	
	
	@Configuration
	public static class LoginControllerTestConfiguration {

		@Bean
		public UserServiceImpl userService() {
			return Mockito.mock(UserServiceImpl.class);
		}

		@Bean
		public LoginController loginController() {
			return new LoginController(userService());
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
