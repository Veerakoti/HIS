package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constants.AppConstants;
import com.example.demo.model.UnlockAcc;
import com.example.demo.model.UserRegistrationModel;
import com.example.demo.service.UserService;

@Controller
public class UnlockUserAccController {
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/unlockAcc")
	public String displayunlockAcc(@RequestParam("email") String email,Model model) {
		//model.addAttribute("email", email);
		UnlockAcc acc=new UnlockAcc();
		
		acc.setEmail(email);
		model.addAttribute(AppConstants.UNLOCK_KEY, acc);
		return AppConstants.UNLOCK_VIEW;
	}
	@RequestMapping(value = "/unlockaccUser")
	public String unlockUserAcc(@ModelAttribute("unlock") UnlockAcc unlockAcc,Model model) {
		try {
			 UserRegistrationModel contactByTempPwd = service.getContactByTempPwd(unlockAcc.getTempPwd(), unlockAcc.getEmail());
			if(contactByTempPwd!=null) {
				contactByTempPwd.setAccStatus(AppConstants.UNLOCKED_STR);
				contactByTempPwd.setPazzword(unlockAcc.getNewPwd());
				boolean updateUserAcc = service.updateUserAcc(contactByTempPwd);
				if(updateUserAcc) {
					return AppConstants.UNLOCK_ACC_SUCC_VIEW;
				}
				else {
				}
			}
			model.addAttribute(AppConstants.ERROR_KEY, AppConstants.ERROR_MSG);

		} catch (Exception e) {
		}
		return AppConstants.UNLOCK_VIEW;
	}

}
