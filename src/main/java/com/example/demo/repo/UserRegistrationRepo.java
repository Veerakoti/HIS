package com.example.demo.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserRegistrationEntity;

public interface UserRegistrationRepo extends JpaRepository<UserRegistrationEntity, Serializable> {
	public UserRegistrationEntity findUserByPazzwordAndEmail(String tempPwd,String email);
	//@Query(name = "from ContactEntity where email=:email")
	public UserRegistrationEntity findByEmail(String email);
	
	public List<UserRegistrationEntity> findByRole(String role);

}
