package com.kgisl.multi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usr_t_user")
@Getter
@Setter
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
	private Integer id;
	
	@Column(name = "usr_user_name")
    private String username;
	
	@Column(name = "usr_password")
    private String password;
	
	@Column(name = "usr_gender")
    private String gender;
	
	@Column(name = "usr_phonenumber")
    private String phonenumber;
	
	@Column(name = "usr_email")
    private String email;

	@Column(name = "usr_active")
    private String active;
}
