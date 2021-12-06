package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.AppConstants;
import com.example.demo.entity.DeleteRegistrationEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.model.UserRegistrationModel;
import com.example.demo.repo.DeleteRegistrationRepo;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRegistrationRepo;
import com.example.demo.utils.EmailUtils;
import com.example.demo.utils.PwdUtils;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRegistrationRepo repo;
	@Autowired
	private DeleteRegistrationRepo deleteRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PwdUtils pwdUtils;
	@Autowired
	private EmailUtils emailUtils;


	@Override
	public List<String> getAllRoles() {
		//Map<Integer,String> map=new LinkedHashMap<Integer, String>(); 
		List<String> roles=new ArrayList<String>();
		List<RoleEntity> entities=roleRepo.findAll(); 
		//System.out.println(countryEntities); 
		for(RoleEntity entity : entities) {
			roles.add(entity.getRoleName());
		}
		//System.out.println(map);

		return roles;
	}
	@Override
	public boolean saveUser(UserRegistrationModel model) {
		model.setPazzword(PwdUtils.generateTempPwd(AppConstants.TEM_PWD_LENGTH));
		model.setAccStatus(AppConstants.LOCKED_STR);
		model.setDeleteSW("N");
		UserRegistrationEntity entity=new UserRegistrationEntity();
		BeanUtils.copyProperties(model, entity);
		UserRegistrationEntity save = repo.save(entity);
		if(save.getId()!=null) {
			return emailUtils.sendUserAccUnlockEmail(model);
		}
		return false;
	}
	@Override
	public UserRegistrationModel getContactByTempPwd(String tempPwd, String email) {
		UserRegistrationEntity findUserByPazzwordAndEmail = repo.findUserByPazzwordAndEmail(tempPwd,email);
		UserRegistrationModel user=null;
		if(findUserByPazzwordAndEmail!=null) {
			user=new UserRegistrationModel();
			BeanUtils.copyProperties(findUserByPazzwordAndEmail, user);	
		}
		return user;
	}

	@Override
	public boolean updateUserAcc(UserRegistrationModel user) {
		UserRegistrationEntity entity=new UserRegistrationEntity();
		BeanUtils.copyProperties(user, entity);
		UserRegistrationEntity save = repo.save(entity);
		return save!=null;
	}

	@Override
	public List<UserRegistrationModel> getAllUsers(String role) {
		List<UserRegistrationModel> users=new ArrayList();
		List<UserRegistrationEntity> entites=repo.findByRole(role);
		for (UserRegistrationEntity entity : entites) {
			UserRegistrationModel user=new UserRegistrationModel();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		return users;
	}
	@Override
	public UserRegistrationModel getUserById(Integer id) {
		Optional<UserRegistrationEntity> findById = repo.findById(id);
		if(findById.isPresent()) {
			UserRegistrationEntity entity = findById.get();
			UserRegistrationModel user=new UserRegistrationModel();
			BeanUtils.copyProperties(entity, user);
			return user;
		}
		return null;
	}
	@Override
	public boolean deleteUser(Integer id) {

		Optional<UserRegistrationEntity> findById = repo.findById(id);
		if(findById.isPresent()) {
			UserRegistrationEntity entity = findById.get();
			entity.setDeleteSW("N");
			repo.save(entity);
			return true;
		}
		return false;
		//deleteRepo.findById(id);
		/*
		 * getUser(id); UserRegistrationEntity entity=new UserRegistrationEntity();
		 * entity.setDeleteSW("N"); repo.saveAndFlush(entity); return true;
		 */
	}
	@Override
	public DeleteRegistrationEntity getUser(Integer id) {
		Optional<UserRegistrationEntity> findById = repo.findById(id);
		System.out.println(findById);
		UserRegistrationEntity userRegistrationEntity = findById.get();
		userRegistrationEntity.setDeleteSW("N");
		System.out.println(userRegistrationEntity);
		DeleteRegistrationEntity target=new DeleteRegistrationEntity();
		BeanUtils.copyProperties(userRegistrationEntity, target);
		DeleteRegistrationEntity save = deleteRepo.save(target);
		System.out.println(save);


		return save;
	}
	@Override
	public boolean activeUser(Integer id) {

		Optional<UserRegistrationEntity> findById = repo.findById(id);
		if(findById.isPresent()) {
			UserRegistrationEntity entity = findById.get();
			entity.setDeleteSW("Y");
			repo.save(entity);
			return true;
		}
		return false;
		/*
		 * UserRegistrationEntity entity=new UserRegistrationEntity();
		 * entity.setDeleteSW("Y"); repo.saveAndFlush(entity); return true;
		 */
	}
	@Override
	public UserRegistrationModel findByEmailAndPazzword(String email) {
		UserRegistrationModel source=new UserRegistrationModel();
		UserRegistrationEntity findByEmail = repo.findByEmail(email);
		BeanUtils.copyProperties(findByEmail, source);
		return source;

	}
	@Override
	public boolean findByEmailAnUnlock(String email) {
		//UserRegistrationModel source=new UserRegistrationModel();
		UserRegistrationEntity user = repo.findByEmail(email);
		//BeanUtils.copyProperties(findByEmail, source);
		if(user.getEmail()!=null && user.getAccStatus().equals("UNLOCKED")) {
			System.out.println(user);
			return emailUtils.sendUserAccPassword(user);
		}
		return false;
	}
}


