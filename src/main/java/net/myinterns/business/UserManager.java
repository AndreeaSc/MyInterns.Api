package net.myinterns.business;

import java.util.List;

import net.andree.MyInterns.common.dto.UserDTO;

public interface UserManager {

	List<UserDTO> getAll();

	UserDTO getById(int id);

	UserDTO saveOrUpdate(final String username, final String password, final Boolean isMentor);

	UserDTO saveOrUpdate(final String username, final String password);

	UserDTO saveOrUpdate(final UserDTO userDTO);

	void delete(long id);

	void deleteByUsername(String username);

	UserDTO login(String username, String password);
}