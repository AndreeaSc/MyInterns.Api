package net.myinterns.persistance.dao;

import java.util.List;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.persistance.entity.Student;
import net.myinterns.persistance.entity.User;

public interface StudentDao {

	List<StudentDTO> getAll();

	Student getById(long id);

	void saveOrUpdate(final String name, final String surname, final String description, final String email,
			final UserDTO user);

	void saveOrUpdateDAO(final String name, final String surname, final String description, final String email,
			final User user);

	void saveOrUpdate(final StudentDTO studentDTO);

	void saveOrUpdateDAO(final Student student);

	void delete(long user_id);

	Student updateByEmail(Student student, String email);
}
