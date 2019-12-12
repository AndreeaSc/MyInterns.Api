package net.andreea.MyInterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.MentorDao;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.User;

@Repository
@Transactional
public class MentorDaoImpl implements MentorDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(final Mentor mentor) {
		new PersistenceOperations().saveOrUpdate(sessionFactory, mentor,
				"*** Mentor '" + mentor.getFirstName() + "' saved!");
	}

	@Override
	public void saveOrUpdateIfMentor(final User user, final Mentor mentor) {
		if (user.getIsMentor() == true) {
			new PersistenceOperations().saveOrUpdate(sessionFactory, mentor,
					"*** Mentor '" + mentor.getFirstName() + "' saved!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readAll() {
		final List<Mentor> detailList = sessionFactory.getCurrentSession().createCriteria(Mentor.class).list();

		System.out.println("************ ALL MENTORSs ****************");

		for (final Mentor detail : detailList) {
			System.out.printf("*** Id:%s \t Firstname:%s \t Lastname:%s \n", detail.getId(), detail.getFirstName(),
					detail.getLastName());
		}
	}

	@Override
	public Mentor getMentor(final User user) {
		final Query q = sessionFactory.getCurrentSession().createQuery("FROM Mentor WHERE user=:user");
		q.setParameter("user", user);

		Mentor mentor = null;

		try {
			mentor = (Mentor) q.uniqueResult();
			if (mentor == null) {
				System.out.println("Mentor with username '" + user.getUsername() + "' Not Found !");
			}
		} catch (Exception ex) {
			System.out.printf("Exception in getMentor: %s \n", ex.getMessage());
		}

		return mentor;
	}

	@SuppressWarnings("unchecked")
	public List<Mentor> getAllMentors() {
		final List<Mentor> detailList = sessionFactory.getCurrentSession().createCriteria(Mentor.class).list();

		System.out.println("************ ALL MENTORSs Get it!!!****************");

		for (final Mentor detail : detailList) {
			System.out.printf("*** Id:%s \t Firstname:%s \t Lastname:%s \n", detail.getId(), detail.getFirstName(),
					detail.getLastName());
		}

		return detailList;
	}

	@Override
	public Mentor getById(int id) {

		final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MentorDao mentorDao = appContext.getBean(MentorDao.class);
		List<Mentor> mentors = new ArrayList<Mentor>();

		mentors = mentorDao.getAll();
		for (Mentor mentor : mentors) {
			if (id == mentor.getId()) {

				return mentor;
			}
		}
		return null;
	}

	@Override
	public void deleteMentor(long id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Mentor mentor = (Mentor) session.load(Mentor.class, id);

		session.delete(mentor);

		session.getTransaction().commit();
	}

	@Override
	public List<Mentor> getAll() {

		final List<Mentor> detailList = sessionFactory.getCurrentSession().createCriteria(Mentor.class).list();

		return detailList;
	}

}
