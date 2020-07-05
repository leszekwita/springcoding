package radzik.michal.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@PropertySource({ "classpath:conf/${app.env}.properties" })
@ComponentScan(basePackages = "radzik.michal", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION))
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
//@PropertySource({ "classpath:conf/local.properties" })
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		//MAX 50mb files
		multipartResolver.setMaxUploadSize(110100480);
		return multipartResolver;
	}
	
	/**
	 * Database connections below
	 */
///////////////////////////////////////////////////////////////////////////////
	@Bean
	public PlatformTransactionManager createSpringCodingWebTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(createSpringCodingEntityManagerFactoryBean().getObject());
		return transactionManager;
	}
	
	@Bean(name = "myEntityManager")
	public LocalContainerEntityManagerFactoryBean createSpringCodingEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(createDataSource());
		em.setPackagesToScan(new String[] { "radzik.michal.model"});
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setPersistenceUnitName("michal");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(createPostgersAdditionalProperties());
		return em;
	}	


	@Bean(name = "myDataSource")
	public DataSource createDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/SpringBase");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}	
		
	
	Properties createPostgersAdditionalProperties() {
		return new Properties() {

			private static final long serialVersionUID = -7125345130577727875L;
			{
				setProperty("hibernate.hbm2ddl.auto", "update");
				setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				setProperty("show_sql", "true");
			}
		};
	}
	/////////////////////////////////////////////////////////////////////////////////
	/**
	 * Database connections above
	 */
	

}
