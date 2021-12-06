package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.UserRegistrationModel;
import com.example.demo.service.UserService;
@Controller
public class ViewUsersController {
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/viewContacts")
	public String viewContacts(HttpServletRequest request, Model model) {
		String roles="Case-Worker";
		 String parameter = request.getParameter("role");
		 if(parameter!=null && !parameter.equals("")) {
			 roles=parameter;
		 }
		
		//String parameter = request.getParameter("role");
	List<UserRegistrationModel> allUsers = service.getAllUsers(roles);
		model.addAttribute("users", allUsers);
		return "viewContacts";
	}
	
	@RequestMapping(value = "/editContact")
	public String editContact(@RequestParam("cid") Integer userId,Model model) {
		UserRegistrationModel userById = service.getUserById(userId);
		model.addAttribute("userform", userById);
		return "loadform";
	}
	@RequestMapping(value = "/deleteContact")
	public String deleteContact(@RequestParam("cid") Integer userId,RedirectAttributes attributes) {
		boolean deleteUser = service.deleteUser(userId);
		if(deleteUser) {
			attributes.addFlashAttribute("success", "User deleted successfully");
			return "redirect:/viewContacts";
		}
		return "loadform";
	}
	@RequestMapping(value = "/active")
	public String activeContact(@RequestParam("cid") Integer userId,RedirectAttributes attributes) {
		boolean deleteUser = service.activeUser(userId);
		if(deleteUser) {
			attributes.addFlashAttribute("success", "User deleted successfully");
			return "redirect:/viewContacts";
		}
		return "loadform";
	}

}
