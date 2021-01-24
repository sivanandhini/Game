package com.kgisl.responsive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "UserFeedback")
@Getter
@Setter
public class UserFeedback {

	@Id
	private String id;

	@Field
	private String feedback;
	
}
