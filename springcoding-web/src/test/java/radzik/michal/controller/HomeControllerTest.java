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
@ContextConfiguration(classes = HomeControllerTest.HomeControllerTestConfiguration.class)
public class HomeControllerTest {
	
	private static final String VIEW_RESOLVER_PREFIX ="/WEB-INF/jsp/";
	
	private static final String VIEW_RESOLVER_SUFFIX =".jsp";
	
	
	 @Autowired 
	 HomeController homeController;
	 
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).setViewResolvers(viewResolver()).build();
	}
	
	
	@Test
	public void getHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
	}
	@Test
	public void getIndex() throws Exception {
		mockMvc.perform(get("/index")).andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
	}
	
	
	
	@Configuration
	public static class HomeControllerTestConfiguration{
		@Bean
		public HomeController homeController() {
			return new HomeController();
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
