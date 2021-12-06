package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PlanRegisterEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.model.PlanRegistrationModel;
import com.example.demo.model.UserRegistrationModel;
import com.example.demo.repo.PlanCreationRepo;
@Service
public class PlanCreationServiceImpl  implements PlanCreationService{
	@Autowired
	private PlanCreationRepo planRepo;

	@Override
	public boolean savePlan(PlanRegistrationModel model) {
		// TODO Auto-generated method stub
		model.setActiveSW("Y");
		PlanRegisterEntity entity=new PlanRegisterEntity();
		BeanUtils.copyProperties(model, entity);
		PlanRegisterEntity save = planRepo.save(entity);
		return save.getPlanId()!=null;
	}

	@Override
	public List<PlanRegistrationModel> getAllPlans() {
		List<PlanRegistrationModel> plans=new ArrayList();
		List<PlanRegisterEntity> findAll = planRepo.findAll();
		for (PlanRegisterEntity planRegisterEntity : findAll) {
			PlanRegistrationModel plan=new PlanRegistrationModel();
			BeanUtils.copyProperties(planRegisterEntity, plan);
			plans.add(plan);
		}

		return plans;
	}

	@Override
	public boolean updateUserAcc(UserRegistrationModel user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlanRegistrationModel getPlanById(Integer id) {
		Optional<PlanRegisterEntity> findById = planRepo.findById(id);
		if(findById.isPresent()) {
			PlanRegisterEntity planRegisterEntity = findById.get();
			PlanRegistrationModel model=new PlanRegistrationModel();
			BeanUtils.copyProperties(planRegisterEntity, model);
			return model;
		}
		return null;
	}

	@Override
	public boolean deletePlan(Integer id) {
		Optional<PlanRegisterEntity> findById = planRepo.findById(id);
		if(findById.isPresent()) {
			PlanRegisterEntity planRegisterEntity = findById.get();
			planRegisterEntity.setActiveSW("N");
			System.out.println("delete  ::"+planRegisterEntity);
			planRepo.save(planRegisterEntity);
			return true;
		}
		return false;
	}

	@Override
	public boolean activePlan(Integer id) {
		Optional<PlanRegisterEntity> findById = planRepo.findById(id);
		if(findById.isPresent()) {
			PlanRegisterEntity planRegisterEntity = findById.get();
			System.out.println("active  ::"+planRegisterEntity);
			planRegisterEntity.setActiveSW("Y");
			System.out.println("active  ::"+planRegisterEntity);
			planRepo.save(planRegisterEntity);
			return true;
		}
		return false;
	}


}
