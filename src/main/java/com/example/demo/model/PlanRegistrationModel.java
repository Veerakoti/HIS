package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class PlanRegistrationModel {
	private Integer planId;
	private String activeSW;
	private Date createdDate;
	private String createdBy;
	private String planDesc;
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date endDate;
	private String planName;
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date startDate;
	private Date updatedDate;
	private String updatedBy;
	
}
