package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;

public interface StudentDao {

	List<StudentDTO> getAll();

	StudentDTO getById(long id);

	void saveOrUpdate(final String name, final String surname, final String description, final String email,
			final UserDTO userDTO);

	void saveOrUpdate(final StudentDTO studentDTO);

	void delete(long id);
}
