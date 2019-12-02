package net.andreea.MyInterns.persistance.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(String studentName, String description) {
		final Student student = new Student(studentName, description);
		saveOrUpdate(student);
	}

	@Override
	public void saveOrUpdate(final Student student) {
		new PersistenceOperations().saveOrUpdate(sessionFactory, student,
				"*** Student '" + student.getStudentName() + "' saved!");
	}

	@Override
	public Student getStudent(final String studentName) {
		final Query q = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE studentName=:studentName");
		q.setParameter("studentName", studentName);

		Student student = null;

		try {
			student = (Student) q.uniqueResult();
			if (student == null) {
				System.out.println("Student with studentName '" + studentName + "' Not Found !");
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
}
