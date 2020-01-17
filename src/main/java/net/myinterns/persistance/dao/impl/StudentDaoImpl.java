package net.myinterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.PersistanceOperations;
import net.myinterns.persistance.dao.StudentDao;
import net.myinterns.persistance.entity.Student;
import net.myinterns.persistance.entity.User;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> getAll() {
		
		final List<Student> studentList = sessionFactory.getCurrentSession().createCriteria(Student.class).list();

		final List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();

		System.out.println("************ ALL Students Get it!!!****************");

		for (Student student : studentList) {
			studentDTOList.add(new StudentDTO(student.getName(), student.getSurname()));
		}

		return studentDTOList;
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
	public void saveOrUpdate(String name, String surname, String description, String email, UserDTO user) {

		final StudentDTO studentDTO = new StudentDTO(name, surname, description, email, user);
		saveOrUpdate(studentDTO);
	}

	@Override
	public void saveOrUpdate(StudentDTO studentDTO) {

		new PersistanceOperations().saveOrUpdate(sessionFactory, studentDTO,
				"*** Student '" + studentDTO.getName() + "' saved!");
	}
	
	public void saveOrUpdateDAO(Student student) {

		new PersistanceOperations().saveOrUpdate(sessionFactory, student,
				"*** Student '" + student.getName() + "' saved!");
	}
	
	public void saveOrUpdateDAO(String name, String surname, String description, String email, User user) {

		final Student student = new Student(name, surname, description, email, user);
		saveOrUpdateDAO(student);
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

	@Override
	public Student updateByEmail(Student student, String email) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Student studentUpdate = null;

		studentUpdate = (Student) session.createCriteria(Student.class).add(Restrictions.eq("email", email))
				.uniqueResult();

		studentUpdate.setName(student.getName());
		studentUpdate.setSurname(student.getSurname());
		studentUpdate.setDescription(student.getDescription());
		studentUpdate.setEmail(student.getEmail());
		System.out.println("a ajuns pana aici" + studentUpdate.toString());
		session.update(studentUpdate);
		tx.commit();
		session.close();

		return studentUpdate;
	}
}