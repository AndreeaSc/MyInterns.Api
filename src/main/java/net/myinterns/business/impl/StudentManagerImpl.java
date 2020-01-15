package net.myinterns.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.StudentManager;
import net.myinterns.persistance.dao.StudentDao;
import net.myinterns.persistance.entity.Student;

@Repository
public class StudentManagerImpl implements StudentManager {

	@Autowired
	StudentDao studentDao;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public List<StudentDTO> getAll() {

		return studentDao.getAll();
	}

	@Override
	public StudentDTO getById(long id) {

		Student student = new Student();
		student = studentDao.getById(id);

		StudentDTO studentDTO = new StudentDTO();

		studentDTO.setName(student.getName());
		studentDTO.setSurname(student.getSurname());
		studentDTO.setDescription(student.getDescription());
		studentDTO.setEmail(student.getEmail());

		return studentDTO;
	}

	@Override
	public StudentDTO saveOrUpdate(String name, String surname, String description, String email, UserDTO userDTO) {
		
		studentDao.saveOrUpdate(name, surname, description, email, userDTO);

		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setName(name);
		studentDTO.setSurname(surname);
		studentDTO.setDescription(description);
		studentDTO.setEmail(email);

		return studentDTO;
	}

	@Override
	public StudentDTO saveOrUpdate(StudentDTO studentDTO) {
	
//		String name = studentDTO.getName();
//		String surname = studentDTO.getSurname();
//		String description = studentDTO.getDescription();
//		String email = studentDTO.getDescription();
//		
//		studentDao.saveOrUpdate(name, surname, description, email);
		
		return null;
	}

	@Override
	public void delete(long id) {

		studentDao.delete(id);
	}
}
