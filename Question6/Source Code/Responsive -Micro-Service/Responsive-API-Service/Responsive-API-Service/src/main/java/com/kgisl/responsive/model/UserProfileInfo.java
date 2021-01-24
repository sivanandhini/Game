package com.kgisl.responsive.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "UserProfileInfo")
@Getter
@Setter
public class UserProfileInfo {

	@Id
	private String id;

	@Indexed
	private String userId;
	
	@Field
	private String resumeId;
	
	@Field
	private String profilePicId;
	
	@Field
	private String name;

	@Field
	private String phoneNumber;
	
	@Field
	private String address;
	
	@Field
	private String gender;

	@Field
	private String dob;
	
	@Field
	private String education;
	
	@Field
	private String emailId;
	
	@Field
	private String languages;
	
	@Field
	private String experience;
	
	@Field
	private String platforms;
	
	@Field
	private String additionalDetails;

    @CreatedDate
    private Date createdDate;
    
    @Field
	private String phoneNo;

}
