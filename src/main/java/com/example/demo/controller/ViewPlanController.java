package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.PlanRegistrationModel;
import com.example.demo.model.UserRegistrationModel;
import com.example.demo.service.PlanCreationService;

@Controller
public class ViewPlanController {
	@Autowired
	private PlanCreationService service;
	
	@GetMapping(value = "/viewPlans")
	public String viewPlans(Model model) {
	 List<PlanRegistrationModel> allPlans = service.getAllPlans();
		model.addAttribute("plans", allPlans);
		return "viewplans";
	}

	@RequestMapping(value = "/editPlan")
	public String editPlan(@RequestParam("pid") Integer planId,Model model) {
		 PlanRegistrationModel planById = service.getPlanById(planId);
		model.addAttribute("plan", planById);
		return "planform";
	}
	@RequestMapping(value = "/deleteplan")
	public String deletePlan(@RequestParam("cid") Integer planId,Model model) {
		boolean delete = service.deletePlan(planId);
		if(delete) {
			model.addAttribute("success", "Plan deleted successfully");
			return "viewplans";
		}
		else {
			model.addAttribute("error", "Plan Active error");
			return "planform";
		}
		
	}
	@RequestMapping(value = "/activeplan")
	public String activePlan(@RequestParam("cid") Integer planId,Model model) {
		boolean active = service.activePlan(planId);
		System.out.println(active);
		if(active) {
			model.addAttribute("success", "Plan Active successfully");
			return "viewplans";
		}
		else {
			model.addAttribute("error", "Plan Active error");
			return "planform";
		}
		
	}

}
