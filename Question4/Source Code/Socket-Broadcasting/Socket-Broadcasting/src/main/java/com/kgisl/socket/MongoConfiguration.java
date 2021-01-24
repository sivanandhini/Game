package com.kgisl.socket;
/*package com.kgisl.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

*//***
 * 		   This class contains the MongoDB Configuration
 *//*

@Configuration
@EnableMongoRepositories(basePackages = MongoConfiguration.MONGO_COLLECTION_BASE_PACKAGE)
@EnableTransactionManagement
@EnableMongoAuditing
@ComponentScan(value = { "com.kgfsl.rest","com.kgfsl.spring" })
@PropertySource("classpath:config/application.properties")

public class MongoConfiguration extends AbstractMongoConfiguration{

	protected static final String MONGO_COLLECTION_BASE_PACKAGE="com.kgfsl.portal.models";

	@Override
	protected String getDatabaseName() {
		return "";
	}

	@Override
	protected String getMappingBasePackage() {
		return MONGO_COLLECTION_BASE_PACKAGE;
	}
	
	*//**
	 * @return mongo object
	 * used to define mongo template
	 *//*
  @Override
  @Bean
  public Mongo mongo() throws Exception {

    List<MongoCredential> auths = new ArrayList<>();
    MongoCredential mongoCred = MongoCredential.createCredential("",
    		"",
    		"");
    auths.add(mongoCred);

    MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(500)
        .connectTimeout(1000).socketKeepAlive(false).cursorFinalizerEnabled(true).maxConnectionLifeTime(50)
        .writeConcern(WriteConcern.ACKNOWLEDGED).threadsAllowedToBlockForConnectionMultiplier(10)
        .readPreference(ReadPreference.primary()).build();

    return new MongoClient(new ServerAddress("",
        1), auths, mongoClientOptions);
  }

	*//**
	 * used to interact with file system
	 * @returnGridFsTemplate
	 * @throws Exception
	 *//*
	@Bean(name="mongoBean")
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

}
*/