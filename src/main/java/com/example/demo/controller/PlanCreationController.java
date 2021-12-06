package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.PlanRegistrationModel;
import com.example.demo.service.PlanCreationService;

@Controller
public class PlanCreationController {
	@Autowired
	private PlanCreationService service;

	@GetMapping(value = "/addPlan")
	public String loadForm(Model model) {
		PlanRegistrationModel registrationModel=new PlanRegistrationModel();
		model.addAttribute("plan", registrationModel);

		return "planform";

	}

	@RequestMapping(value = "/planCreation",method = RequestMethod.POST)
	public String handleSubmitBtn(@ModelAttribute("plan") PlanRegistrationModel	model,RedirectAttributes attributes) { 
		boolean savePlan =	service.savePlan(model); 
		if(savePlan) {
			attributes.addFlashAttribute("successMsg", "Plan Saved Successfully");
		}
		else { 
			attributes.addFlashAttribute("errorMsg", "Plan Saving Failed"); 
			}

		return "redirect:/addPlan";

	}

}
