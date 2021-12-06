package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.model.UserRegistrationModel;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService service;
	@GetMapping(value =  "/")
	public String loadForm(Model model) {
		UserRegistrationModel user=new UserRegistrationModel();
		model.addAttribute("userform", user);

		return "login";

	}
	@RequestMapping(value =  "/loginuser",method = RequestMethod.POST)
	public String welcomeForm(@RequestParam String email,@RequestParam String pazzword,Model model) {
		UserRegistrationModel user = service.findByEmailAndPazzword(email);
		System.out.println(user);
		if(user.getPazzword().equals(pazzword)) {
			model.addAttribute("user",email); 
			if(user.getAccStatus().equals("UNLOCKED")) {
				model.addAttribute("acc","UNLOCKED"); 
				if(user.getDeleteSW().equals("N")) {
					model.addAttribute("delete","N       value");
					if(user.getRole().equals("Admin")) {
						model.addAttribute("role","Admin");
						return "admin";
					}
					else {
						model.addAttribute("er","Case worker");
						return "caseworker";
					}

				}else {
					model.addAttribute("rd","Y       value");
				}

			}else {
				model.addAttribute("eac","LOCKED");
			}

		}


		/*
		 * if(userId.equals("admin")&& pass.equals("pass")) { model.addAttribute("user",
		 * userId); return "welcome"; }
		 */
		model.addAttribute("error", "both are not correct");

		return "login";

	}
	
	
	@GetMapping(value =  "/forgotPassword")
	public String forgotPasswordForm(Model model) {
		UserRegistrationModel user=new UserRegistrationModel();
		model.addAttribute("userform", user);

		return "forgotpassword";

	}
	@RequestMapping(value =  "/forgotPasswordSendEmail",method = RequestMethod.POST)
	public String forgotPasswordFormHandleBtn(@RequestParam String email,Model model) {
		 boolean findByEmailAnUnlock = service.findByEmailAnUnlock(email);
		//System.out.println(user.getPazzword());
		if(findByEmailAnUnlock) {
			model.addAttribute("userform", "send to mail");
			return "sendpassword";
		}
		
			model.addAttribute("error","user not found");
			return "forgotpassword";

	}


}
