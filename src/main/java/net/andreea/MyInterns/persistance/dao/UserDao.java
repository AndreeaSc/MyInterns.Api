package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andree.MyInterns.common.dto.UserDTO;

public interface UserDao {

	List<UserDTO> getAll();

	UserDTO getById(long id);

	void saveOrUpdate(String username, String password, Boolean isMentor);

	void saveOrUpdate(String username, String password);

	void saveOrUpdate(final UserDTO userDTO);

	void delete(long id);
}
