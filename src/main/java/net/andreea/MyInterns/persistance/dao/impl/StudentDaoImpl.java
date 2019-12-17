package net.andreea.MyInterns.persistance.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Student> getAll() {
		final List<Student> detailList = sessionFactory.getCurrentSession().createCriteria(Student.class).list();

		System.out.println("************ ALL Students Get it!!!****************");

		for (final Student detail : detailList) {
			System.out.printf("*** Id:%s \t Name:%s \t Surname:%s \n", detail.getId(), detail.getName(),
					detail.getSurname());
		}

		return detailList;
	}

	@Override
	public Student getById(long id) {

		Student student = null;

		Query q = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE id=:id").setParameter("id", id);

		try {
			student = (Student) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in getStudentbyId: %s \n", ex.getMessage());
		}

		return student;
	}

	@Override
	public void saveOrUpdate(String name, String surname, String description, String email, User user) {

		final Student student = new Student(name, surname, description, email, user);
		saveOrUpdate(student);
	}

	@Override
	public void saveOrUpdate(Student student) {
		
		new PersistenceOperations().saveOrUpdate(sessionFactory, student,
		"*** Student '" + student.getName() + "' saved!");
	}

	@Override
	public void delete(long id) {

		Student student = null;

		Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Student WHERE id=:id").setParameter("id",
				id);

		try {
			student = (Student) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in deleteStudent: %s \n", ex.getMessage());
		}

		q.executeUpdate();
	}

}