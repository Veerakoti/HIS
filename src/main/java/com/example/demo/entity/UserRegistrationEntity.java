package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Entity
@Table(name = "USER_REGISTRATION")
@Data
public class UserRegistrationEntity {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer id;
	@Column(name="ACC_STATUS")
	private String accStatus;
	@Column(name="DELETE_SW")
	private String deleteSW;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dob;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "ROLE")
	private String role;
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column(name = "UPDATED_DATE",insertable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Column(name = "USER_PWD")
	private String pazzword;	

}
