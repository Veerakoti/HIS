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
@Table(name = "PLAN_DTLS")
@Data
public class PlanRegisterEntity {
	@Id
	@GeneratedValue
	@Column(name = "Plan_Id")
	private Integer planId;
	
	@Column(name = "Active_Sw")
	private String activeSW;
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Plan_Desc")
	private String planDesc;
	
	@Column(name = "End_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date endDate;
	
	@Column(name = "Plan_Name")
	private String planName;
	
	@Column(name = "Start_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date startDate;
	
	@Column(name = "UPDATED_DATE",insertable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	

}
