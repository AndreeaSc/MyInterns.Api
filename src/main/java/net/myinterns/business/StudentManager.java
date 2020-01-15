package net.myinterns.business;

import java.util.List;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;

public interface StudentManager {

	List<StudentDTO> getAll();

	StudentDTO getById(long id);

	StudentDTO saveOrUpdate(final String name, final String surname, final String description, final String email,
			final UserDTO userDTO);

	StudentDTO saveOrUpdate(final StudentDTO studentDTO);

	StudentDTO updateByEmail(StudentDTO studentDTO, String email);
	
	void delete(long id);
}
