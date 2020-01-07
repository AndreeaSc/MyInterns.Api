package net.myinterns.business;

import java.util.List;

import net.andree.MyInterns.common.dto.UserDTO;

public interface UserManager {
	
	List<UserDTO> getAll();

	UserDTO getById(int id);

	void saveOrUpdate(final String username, final String password, final Boolean isMentor);

	void saveOrUpdate(final String username, final String password);

	void saveOrUpdate(final UserDTO userDTO);

	void delete(int id);
	
	UserDTO login(String username, String password);
}
