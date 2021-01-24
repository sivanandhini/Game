package com.kgisl.multi.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Maps;

@Configuration
@ComponentScan(value = { "com.kgisl.multi" })
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages={"com.kgisl.multi.modal"})
public class TenantDBConfig {
	
	@Value("${spring.default.datasource.url}")
	private String defaultDBUrl;
	
	@Value("${spring.default.datasource.username}")
	private String defaultDBUsername;
	
	@Value("${spring.default.datasource.password}")
	private String defaultDBPassword;
	
	private JdbcTemplate jdbcTemplate;
	
	@Bean(name = "defaultDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();       
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(defaultDBUrl);
        dataSource.setUsername(defaultDBUsername);
        dataSource.setPassword(defaultDBPassword);
        return dataSource;
    }

	@Bean(name = "tenantDataSources")
    public DataSource tenantDataSources(@Autowired @Qualifier("defaultDataSource") DataSource dataSource) {
				
		AbstractRoutingDataSource dynamicDataSource=new SessionRoutingDataSource();
		
		Map<Object, Object> dataSources = getTenantDataSources(dataSource);
		
		dynamicDataSource.setTargetDataSources(dataSources);
		
		dynamicDataSource.setDefaultTargetDataSource(dataSource);
	
		return dynamicDataSource;
    }

	private Map<Object, Object> getTenantDataSources(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);

		String query = "select * from clt_data_sources";
		Map<Object, Object> dsmap = Maps.newHashMap();

		jdbcTemplate.query(query, new ResultSetExtractor<HashMap<String, String>>() {
			@Override
			public HashMap<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					DriverManagerDataSource dataSource = new DriverManagerDataSource();
					dataSource.setDriverClassName("com.mysql.jdbc.Driver");
					dataSource.setUrl(rs.getString("clt_ds_url"));
					dataSource.setUsername(rs.getString("clt_ds_username"));
					dataSource.setPassword(rs.getString("clt_ds_password"));
					dsmap.put(rs.getString("clt_ds_tenant_id"), dataSource);
				}
				return null;
			}
		});

		return dsmap;
	}
	
	@Bean(name ="defaultSessionFactory")
	public LocalSessionFactoryBean sessionFactory(@Autowired @Qualifier("defaultDataSource") DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.kgisl.multi");
		return sessionFactory;
	}
	
	@Bean(name ="tenantSessionFactory")
	public LocalSessionFactoryBean tenantSessionFactory(@Autowired @Qualifier("tenantDataSources") DataSource tenantDataSources) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(tenantDataSources);
		sessionFactory.setPackagesToScan("com.kgisl.multi");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "false");
		properties.put("hibernate.hbm2ddl.auto", "none");
		return properties;
	}
	
	@Bean(name="defaultTransactionManager")
	public HibernateTransactionManager defaulttransactionManager(@Autowired @Qualifier("defaultSessionFactory") SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	@Bean(name="tenantTransactionManager")
	public HibernateTransactionManager tenantTransactionManager(@Autowired @Qualifier("tenantSessionFactory") SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
}
