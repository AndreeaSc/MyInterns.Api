package net.myinterns.business.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.UserManager;
import net.myinterns.persistance.dao.UserDao;

@Repository
public class UserManagerImpl implements UserManager {

//	@Autowired
//	private UserDao userDao;
	
	final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao userDao = appContext.getBean(UserDao.class);
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public List<UserDTO> getAll() {

		if (null == userDao) {
			System.out.println("user dao null");		}

		return userDao.getAll();
	}

	

	@Override
	public UserDTO getById(int id) {
		userDao.getById(id);
		return new UserDTO();
	}

	@Override
	public void saveOrUpdate(final String username, final String password, final Boolean isMentor) {

		userDao.saveOrUpdate(username, password, isMentor);
	}

	@Override
	public void saveOrUpdate(final String username, final String password) {

		userDao.saveOrUpdate(username, password);
	}

	@Override
	public void saveOrUpdate(final UserDTO userDTO) {

//		userDTO.setIsMentor(userDTO.getIsMentor());
//		userDTO.setPassword(userDTO.getPassword());
//		userDTO.setUsername(userDTO.getUsername());

		String password = userDTO.getPassword();
		String username = userDTO.getUsername();
		Boolean isMentor = userDTO.getIsMentor();

		saveOrUpdate(password, username, isMentor);
	}

	@Override
	public void delete(int id) {

		userDao.delete(id);
	}
}
