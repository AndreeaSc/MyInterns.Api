package net.andreea.MyInterns.persistance.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.User;
import net.andreea.MyInterns.persistance.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(String username, String password, Boolean isMentor) {
		final User user = new User(username, password, isMentor);
		saveOrUpdate(user);

	}
	
	@Override
	public void saveOrUpdate(final User user) {
		new PersistenceOperations().saveOrUpdate(sessionFactory, user, "*** User '" + user.getEmail() + "' saved!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

		System.out.println("************ ALL USERs ****************");

		for (final User user : userList) {
			System.out.printf("*** Id:%s \t Email:%s \t Password:%s  isMentor:%s \n", user.getId(), user.getEmail(),
					user.getPassword(), user.getIsMentor());
		}
		
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readAll() {
		final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

		System.out.println("************ ALL USERs ****************");

		for (final User user : userList) {
			System.out.printf("*** Id:%s \t Email:%s \t Password:%s  isMentor:%s \n", user.getId(), user.getEmail(),
					user.getPassword(), user.getIsMentor());
		}
	}

	@Override
	public User getUser(String email, String password) {
		final Query q = sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE email=:email AND password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);

		User user = null;

		try {
			user = (User) q.uniqueResult();
			if (user == null) {
				System.out.println("User with email '" + email + "' Not Found !");
			}
		} catch (Exception ex) {
			System.out.printf("Exception in getUser: %s \n", ex.getMessage());
		}

		return user;
	}
	
	@Override
	public User getById(long id) {
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
	public void delete(long id) {
		User user = null;

		Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id=:id").setParameter("id", id);

		try {
			user = (User) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in deleteStudent: %s \n", ex.getMessage());
		}
		
		q.executeUpdate();

	}


}
