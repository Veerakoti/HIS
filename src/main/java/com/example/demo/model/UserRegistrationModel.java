package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class UserRegistrationModel {
	private Integer id;
	private String accStatus;
	private String deleteSW;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dob;
	private String role;
	private Date createdDate;
	private Date updatedDate;
	private String pazzword;

}
