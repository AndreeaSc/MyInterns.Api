package net.myinterns.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.UserDTO;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.myinterns.business.UserManager;

@Repository
public class UserManagerImpl implements UserManager{

	@Autowired
	UserDao userDao;
	
	@Override
	public List<UserDTO> getAll() {

		return userDao.getAll();
	}

	@Override
	public UserDTO getById(long id) {
		
		return userDao.getById(id);
	}

	@Override
	public void saveOrUpdate(String username, String password, Boolean isMentor) {

		userDao.saveOrUpdate(username, password, isMentor);
	}

	@Override
	public void saveOrUpdate(String username, String password) {

		userDao.saveOrUpdate(username, password);
	}

	@Override
	public void saveOrUpdate(UserDTO userDTO) {
		
		userDao.saveOrUpdate(userDTO);
	}

	@Override
	public void delete(long id) {

		userDao.delete(id);
	}
}
