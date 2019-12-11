package net.andreea.MyInterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(String name, String description) {
		final Student student = new Student(name, description);
		saveOrUpdate(student);
	}

	@Override
	public void saveOrUpdate(final Student student) {
		new PersistenceOperations().saveOrUpdate(sessionFactory, student,
				"*** Student '" + student.getName() + "' saved!");
	}

	@Override
	public Student getStudent(final String name) {
		final Query q = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE name=:name");
		q.setParameter("name", name);

		Student student = null;

		try {
			student = (Student) q.uniqueResult();
			if (student == null) {
				System.out.println("Student with name '" + name + "' Not Found !");
			}
		} catch (Exception ex) {
			System.out.printf("Exception in getStudent: %s \n", ex.getMessage());
		}
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> getMentorStudents(Mentor mentor) {
		final Set<Student> studentSet = new HashSet<>();

		final Query q = sessionFactory.getCurrentSession()
				.createQuery("SELECT s FROM Mentor p JOIN p.students s WHERE p.id=:mentorId");
		q.setParameter("mentorId", mentor.getId());

		try {
			studentSet.addAll((List<Student>) q.list());
		} catch (Exception ex) {
			System.out.printf("Exception in getMentorStudents: %s \n", ex.getMessage());
		}

		return studentSet;
	}

	@Override
	public List<Student> getAll() {

		final List<Student> detailList = sessionFactory.getCurrentSession().createCriteria(Student.class).list();

		return detailList;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	public void deleteStudent(long id) {
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