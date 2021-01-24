package com.kgisl.responsive.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kgisl.responsive.model.UserFeedback;
import com.mongodb.DBCollection;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class UserProfileService {
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;

	public String saveUserFile(MultipartFile resume) throws IOException {
		
		DBCollection collections = mongoTemplate.collectionExists("ProfileFiles") ? mongoTemplate.getCollection("ProfileFiles") : mongoTemplate.createCollection("ProfileFiles");
		
		GridFS gridFs = new GridFS(mongoTemplate.getDb(), mongoTemplate.getCollection("ProfileFiles").toString());

		GridFSInputFile gridFsInputFile = gridFs.createFile(resume.getInputStream());

		gridFsInputFile.setFilename(resume.getOriginalFilename());
		gridFsInputFile.setContentType(resume.getContentType());
		gridFsInputFile.save();

		return gridFsInputFile.getId().toString();
	}

	public void saveFeedback(String feedback) {

		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setFeedback(feedback);
		
		mongoOperations.save(userFeedback);
	}

}
