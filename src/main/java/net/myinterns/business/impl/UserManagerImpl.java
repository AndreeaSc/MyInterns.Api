package net.myinterns.business.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.UserManager;
import net.myinterns.persistance.dao.UserDao;
import net.myinterns.persistance.entity.User;

@Repository
public class UserManagerImpl implements UserManager {

	final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao userDao = appContext.getBean(UserDao.class);

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserDTO> getAll() {

		if (null == userDao) {
			System.out.println("user dao null");
		}

		return userDao.getAll();
	}

	@Override
	public UserDTO getById(int id) {
		
		userDao.getById(id);
		
		return new UserDTO();
	}

	@Override
	public UserDTO saveOrUpdate(final String username, final String password, final boolean isMentor) {

		userDao.saveOrUpdate(username, password, isMentor);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setIsMentor(isMentor);
		
		return userDTO;
	}

	@Override
	public UserDTO saveOrUpdate(final String username, final String password) {

		userDao.saveOrUpdate(username, password);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);

		return userDTO;
	}

	@Override
	public UserDTO saveOrUpdate(final UserDTO userDTO) {

		String password = userDTO.getPassword();
		String username = userDTO.getUsername();
		boolean isMentor = userDTO.getIsMentor();

		saveOrUpdate(username, password, isMentor);
				
		return userDTO;
	}

	@Override
	public void delete(long id) {

		userDao.delete(id);
	}

	
	@Override
	public void deleteByUsername(String username) {

		userDao.deleteByUsername(username);
	}
	
	@Override
	public UserDTO login(String username, String password) {
		try {
			User userObj = userDao.getUser(username, password);

			if (userObj.getUsername() != null) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(userObj.getUsername());

				return userDTO;

			} else {
				System.out.println("The username or the password is incorrect!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}