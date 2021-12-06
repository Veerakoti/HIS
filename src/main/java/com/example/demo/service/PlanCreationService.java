package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PlanRegistrationModel;
import com.example.demo.model.UserRegistrationModel;

public interface PlanCreationService {
	public boolean savePlan(PlanRegistrationModel model);
	public List<PlanRegistrationModel> getAllPlans();
	public boolean updateUserAcc(UserRegistrationModel user);
	public PlanRegistrationModel getPlanById(Integer id);
	public boolean deletePlan(Integer id);
	public boolean activePlan(Integer id);


}
