package com.kgisl.di.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class BeanConfiguration {

	@Bean("strBean1")
	@Order(1)
	public String getStringBean1(){
		
		return "This is bean 1 of String type";
	}
	
	@Bean("strBean2")
	@Order(2)
	public String getStringBean2(){
		
		return "This is a bean 2 of String type";
	}
	
	@Bean
	public Integer getIntegerBean(){
		
		return 32;
	}
	
	@Autowired
	DataSource dataSource;
	
	@Bean(name ="sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.kgisl.di");
		return sessionFactory;
	}
	
}
