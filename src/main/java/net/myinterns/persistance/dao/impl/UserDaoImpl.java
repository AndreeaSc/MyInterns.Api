package net.myinterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.PersistanceOperations;
import net.myinterns.persistance.dao.UserDao;
import net.myinterns.persistance.entity.User;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

	static Session sessionObj;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<UserDTO> getAll() {

		final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

		final List<UserDTO> userDTOList = new ArrayList<UserDTO>();

		for (User user : userList) {
			userDTOList.add(new UserDTO(user.getUsername(), user.getPassword(), user.getIsMentor()));
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

	@Override
	public User loginCheck(String username, String password) {

		User userObj = null;
		try {
			System.out.println("finding user");

			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			userObj = getUser(username, password);
			System.out.println("Start search");
			System.out.println(userObj);
			if (userObj.getUsername().equals(username) && userObj != null) {

				System.out.println("Correct username");
				System.out.println(userObj.toString());

				if (userObj.getPassword().equals(password)) {
					System.out.println("Well done!");
					return userObj;
				} else {
					System.out.println("Wrong password");
					return null;
				}

			} else {
				System.out.println("Wrong username or wrong password");
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
			return null;
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	@Override
	public User getUser(String username, String password) {
		final Query q = sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE username=:username AND password=:password");
		q.setParameter("username", username);
		q.setParameter("password", password);

		User user = null;

		try {
			user = (User) q.uniqueResult();
			if (user == null) {
				System.out.println("User with username '" + username + "' Not Found !");
			}
		} catch (Exception ex) {
			System.out.printf("Exception in getUser: %s \n", ex.getMessage());
		}

		return user;
	}
}