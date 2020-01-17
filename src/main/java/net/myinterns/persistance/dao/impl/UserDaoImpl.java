package net.myinterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.PersistanceOperations;
import net.myinterns.persistance.dao.UserDao;
import net.myinterns.persistance.entity.Student;
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
	public User getById(long id) {

		User user = null;

		Query q = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id=:id").setParameter("id", id);

		try {
			user = (User) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in getUserById: %s \n", ex.getMessage());
		}

		return user;
	}

	@Override
	public void delete(UserDTO userDTO) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Long id = userDTO.getId();
		User user = null;
		user = (User) session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
		Query q = session.createQuery("Select uc FROM Student uc JOIN uc.user u WHERE u.id=:id");
		q.setParameter("id", id);
		List<Student> us = new ArrayList<Student>();
		try {
			us.addAll(q.list());
		} catch (Exception e) {
		}
		for (Student student : us) {
			session.delete(student);
		}

		session.delete(user);
		session.getTransaction().commit();

		System.out.println("Deleted Successfully");
	}

	@Override
	public void saveOrUpdate(String username, String password, boolean isMentor) {

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

			sessionObj = this.sessionFactory.openSession();
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

	@Override
	public void deleteByUsername(UserDTO userDTO) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String username = userDTO.getUsername();
		User user = null;
		user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
		Query q = session.createQuery("Select us FROM Student us JOIN us.user u WHERE u.username=:username");
		q.setParameter("username", username);
		List<Student> us = new ArrayList<Student>();
		try {
			us.addAll(q.list());
		} catch (Exception e) {
		}
		for (Student student : us) {
			session.delete(student);
		}

		session.delete(user);
		session.getTransaction().commit();

		System.out.println("Deleted Successfully");
	}

	@Override
	public User getByUsername(String username) {
		User user = null;

		Query q = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username")
				.setParameter("username", username);

		try {
			user = (User) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in getUserByUsername: %s \n", ex.getMessage());
		}

		return user;
	}

	@Override
	public User update(User user, long id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User userUpdated = null;

		userUpdated = (User) session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();

		userUpdated.setUsername(user.getUsername());
		userUpdated.setPassword(user.getPassword());
		userUpdated.setId(user.getId());
		session.update(userUpdated);
		tx.commit();
		session.close();

		return userUpdated;
	}

	@Override
	public User updateByUsername(User user, String username) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User userUpdated = null;

		userUpdated = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username))
				.uniqueResult();

		userUpdated.setUsername(user.getUsername());
		userUpdated.setPassword(user.getPassword());
		session.update(userUpdated);
		tx.commit();
		session.close();

		return userUpdated;
	}
}
