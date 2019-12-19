package net.myinterns.business;

import java.util.List;

import net.andree.MyInterns.common.dto.StudentDTO;

public interface StudentManager {

	List<StudentDTO> getAll();

	StudentDTO getById(long id);

//	void saveOrUpdate(final String name, final String surname, final String description, final String email,
//			final User user);

	void saveOrUpdate(final StudentDTO student);

	void delete(long id);
}
