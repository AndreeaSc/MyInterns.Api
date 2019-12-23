package net.myinterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.PersistanceOperations;
import net.myinterns.persistance.dao.UserDao;
import net.myinterns.persistance.entity.User;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserDTO> getAll() {
		
		final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

		final List<UserDTO> userDTOList = new ArrayList<UserDTO>();

		for(User user : userList) {
			userDTOList.add(new UserDTO(user.getUsername(),user.getPassword(), user.getIsMentor()));
		}
		
		return userDTOList;
	}

	@Override
	public User getById(int id) { 

		User user = null;

		Query q = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id=:id").setParameter("id", id);

		try {
			user = (User) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in getStudentbyId: %s \n", ex.getMessage());
		}

		return user;
	}

	@Override
	public void delete(int id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserDTO user = (UserDTO) session.load(UserDTO.class, id);

		session.delete(user);

		session.getTransaction().commit();
	}

	@Override
	public void saveOrUpdate(String username, String password, Boolean isMentor) {

		final User user = new User(username, password, isMentor);
		saveOrUpdate(user);
	}

	@Override
	public void saveOrUpdate(String username, String password) {

		final User user = new User(username, password);
		saveOrUpdate(user);
	}
	
	@Override
	public void saveOrUpdate(User user) {
		
		new PersistanceOperations().saveOrUpdate(sessionFactory, user, "*** User '" + user.getUsername() + "' saved!");
	}
}
