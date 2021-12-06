package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DeleteRegistrationEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.model.UserRegistrationModel;

public interface UserService {
	public List<String> getAllRoles();
	
	public boolean saveUser(UserRegistrationModel model);
	
	public UserRegistrationModel getContactByTempPwd(String tempPwd,String email);
	
	public boolean updateUserAcc(UserRegistrationModel user);
	
	public List<UserRegistrationModel> getAllUsers(String role);
	
	public UserRegistrationModel getUserById(Integer id);
	
	public boolean deleteUser(Integer id);
	
	public boolean activeUser(Integer id);
	
	public DeleteRegistrationEntity getUser(Integer id);
	
	public UserRegistrationModel findByEmailAndPazzword(String email);
	
	public boolean findByEmailAnUnlock(String email);

	

}
