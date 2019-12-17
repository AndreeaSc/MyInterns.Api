package net.andreea.MyInterns.persistance.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

		System.out.println("************ ALL USERs ****************");

		for (final User user : userList) {
			System.out.printf("Username:%s \t \n", user.getUsername().toString());
		}

		return userList;
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

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		User user = (User) session.load(User.class, id);

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

		new PersistenceOperations().saveOrUpdate(sessionFactory, user, "*** User '" + user.getUsername() + "' saved!");
	}
}
