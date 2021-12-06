package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.UserRegistrationModel;
import com.example.demo.service.UserService;

@Controller
public class UserCreationController {
	@Autowired
	private UserService service;
	@GetMapping(value =  "/addUser")
	public String loadForm(Model model) {
		UserRegistrationModel user=new UserRegistrationModel();
		model.addAttribute("userform", user);
	List<String> allRoles = service.getAllRoles();
		model.addAttribute("roles", allRoles);
		
		
		return "loadform";
		
	}
	@RequestMapping(value = "/userAccReg",method = RequestMethod.POST)
	public String handleSubmitBtn(@ModelAttribute("userform") UserRegistrationModel model) {
		service.saveUser(model);
		return "registrationSuccess";
		
	}

	

}
