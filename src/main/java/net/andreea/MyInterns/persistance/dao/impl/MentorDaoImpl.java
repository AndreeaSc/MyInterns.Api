package net.andreea.MyInterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
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
	public List<Mentor> getAll() {

		final List<Mentor> detailList = sessionFactory.getCurrentSession().createCriteria(Mentor.class).list();

		System.out.println("************ ALL MENTORSs Get it!!!****************");

		for (final Mentor detail : detailList) {
			System.out.printf("*** Id:%s \t Name:%s \t Surname:%s \n", detail.getId(), detail.getName(),
					detail.getSurname());
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
	public void saveOrUpdate(String name, String surname, String email, String qualification, Boolean isExternal,
			User user) {
		
		final Mentor mentor = new Mentor(name, surname, email, qualification, isExternal, user);
		saveOrUpdate(mentor);
	}

	@Override
	public void saveOrUpdate(Mentor mentor) {
		new PersistenceOperations().saveOrUpdate(sessionFactory, mentor,
		"*** Student '" + mentor.getName() + "' saved!");
	}

	@Override
	public void delete(long id) {
		
		Mentor mentor = null;

		Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Mentor WHERE id=:id").setParameter("id",
				id);

		try {
			mentor = (Mentor) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in deleteMentor: %s \n", ex.getMessage());
		}

		q.executeUpdate();
	}
}
