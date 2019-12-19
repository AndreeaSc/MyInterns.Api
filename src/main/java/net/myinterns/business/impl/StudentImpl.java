package net.myinterns.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.entity.Student;
import net.myinterns.business.StudentManager;

@Repository
public class StudentImpl implements StudentManager {

	@Autowired
	StudentDao studentDao;

	@Override
	public List<StudentDTO> getAll() {
		
		List<Student> students = studentDao.getAll();
		List<StudentDTO> studentsDTO = new ArrayList<StudentDTO>();
		
		for (Student student : students) {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setName(student.getName());
			studentDTO.setSurname(student.getSurname());
			studentDTO.setEmail(student.getEmail());
			studentDTO.setDescription(student.getDescription());
			studentsDTO.add(studentDTO);
		}
		return studentsDTO;
	}

	@Override
	public StudentDTO getById(long id) {

		StudentDTO studentDTO = new StudentDTO();
		Student student = studentDao.getById(id);
		
		studentDTO.setName(student.getName());
		studentDTO.setSurname(student.getSurname());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setDescription(student.getDescription());
		
		return studentDTO;
	}

//	@Override
//	public void saveOrUpdate(String name, String surname, String description, String email, Student user) {
//
//		studentDao.saveOrUpdate(name, surname, description, email, user);
//	}

	@Override
	public void saveOrUpdate(StudentDTO studentDTO) {

	}

	@Override
	public void delete(long id) {

		studentDao.delete(id);
	}
}
