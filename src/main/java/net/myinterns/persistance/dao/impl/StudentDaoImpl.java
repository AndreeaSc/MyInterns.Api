package net.myinterns.persistance.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.PersistanceOperations;
import net.myinterns.persistance.dao.StudentDao;
import net.myinterns.persistance.entity.Student;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> getAll() {
		final List<StudentDTO> detailList = sessionFactory.getCurrentSession().createCriteria(Student.class).list();

		System.out.println("************ ALL Students Get it!!!****************");

		for (final StudentDTO detail : detailList) {
			System.out.printf("*** Id:%s \t Name:%s \t Surname:%s \n", detail.getId(), detail.getName(),
					detail.getSurname());
		}

		return detailList;
	}
	
	@Override
	public StudentDTO getById(long id) {

		StudentDTO studentDTO = null;

		Query q = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE id=:id").setParameter("id", id);

		try {
			studentDTO = (StudentDTO) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in getStudentbyId: %s \n", ex.getMessage());
		}

		return studentDTO;
	}

	@Override
	public void saveOrUpdate(String name, String surname, String description, String email, UserDTO userDTO) {

		final StudentDTO student = new StudentDTO(name, surname, description, email, userDTO);
		saveOrUpdate(student);
	}

	@Override
	public void saveOrUpdate(StudentDTO studentDTO) {

		new PersistanceOperations().saveOrUpdate(sessionFactory, studentDTO,
				"*** Student '" + studentDTO.getName() + "' saved!");
	}

	@Override
	public void delete(long id) {

		StudentDTO studentDTO = null;

		Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Student WHERE id=:id").setParameter("id",
				id);

		try {
			studentDTO = (StudentDTO) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in deleteStudent: %s \n", ex.getMessage());
		}

		q.executeUpdate();
	}

}