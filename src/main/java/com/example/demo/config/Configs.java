package com.example.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.example.demo.controller.DemoServiceController;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.impl.EmployeeServiceImpl;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

/**
 * 
 * Configuration file to define all beans
 * Contains bean definition for database connection,hibernate session factory and hibernate transactions
 * 
 */

@Configuration
public class Configs {
	
	@Autowired
    private ApplicationContext context;
	
	@Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;
    
    @Value("${hibernate.connection.driver_class}")
    private String CONNECTION_DRIVER_CLASS;
	
	@Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new SpringServlet(), "/app/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, ServerInitialization.class.getName());
        return registration;
    }
	
	@Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
    	return DataSourceBuilder.create().build();
    }
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
      Properties hibernateProperties = new Properties();
      hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
      hibernateProperties.put("hibernate.connection.driver_class", CONNECTION_DRIVER_CLASS);
      
      LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
      sessionFactoryBean.setDataSource(dataSource());
      sessionFactoryBean.setHibernateProperties(hibernateProperties);
      Resource resource = context.getResource("classpath:hibernate.cfg.xml");
      sessionFactoryBean.setConfigLocation(resource);
      
      return sessionFactoryBean;
    }
	
	@Bean
    public HibernateTransactionManager transactionManager() {
      HibernateTransactionManager transactionManager = 
          new HibernateTransactionManager();
      transactionManager.setSessionFactory(sessionFactory().getObject());
      return transactionManager;
    }
	
	@Bean
    public DemoServiceController demoServiceController(){
    	return new DemoServiceController();
    }
	
	@Bean
    public EmployeeService employeeService(){
    	return new EmployeeServiceImpl();
    }
}
