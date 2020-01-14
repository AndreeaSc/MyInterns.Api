package net.myinterns.business;

import java.util.List;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.persistance.entity.User;

public interface UserManager {

	List<UserDTO> getAll();

	UserDTO getById(long id);

	UserDTO saveOrUpdate(final String username, final String password, final boolean isMentor);

	UserDTO saveOrUpdate(final String username, final String password);

	UserDTO saveOrUpdate(final UserDTO userDTO);

	UserDTO update(UserDTO user, long id);

	void delete(long id);

	void deleteByUsername(String username);

	UserDTO login(String username, String password);

	UserDTO getByUsername(String username);
}