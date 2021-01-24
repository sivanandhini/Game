package com.kgisl.di.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usr_user")
@Getter
@Setter
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_first_name")
    private String userFirstName;
	
	@Column(name = "user_last_name")
    private String userLastName;
	
	@Column(name = "user_phone_number")
    private String userPhoneNumber;
	
	@Column(name = "user_email")
    private String userEmailId;
	
	@Column(name = "user_gender")
	private String userGender;
	
}
