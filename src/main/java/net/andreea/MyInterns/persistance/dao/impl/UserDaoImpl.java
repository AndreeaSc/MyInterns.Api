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

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(String username, String password, Mentor mentor) {
		final User user = new User(username, password);
		new PersistenceOperations().saveOrUpdate(sessionFactory, user, "*** User saved!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readAll() {
		final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

		System.out.println("************ ALL USERs ****************");

		for (final User user : userList) {
			System.out.printf("*** Id:%s \t Username:%s \t Password:%s  Mentor:%s \n", user.getId(), user.getUsername(),
					user.getPassword(), user.getMentor().getFirstName() + " " + user.getMentor().getLastName());
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
	public void delete() {
		// TODO Auto-generated method stub

	}
}
