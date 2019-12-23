package net.myinterns.persistance.dao;

import java.util.List;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.persistance.entity.User;

public interface UserDao {

	List<UserDTO> getAll();

	User getById(int id);

	void saveOrUpdate(String username, String password, Boolean isMentor);

	void saveOrUpdate(String username, String password);

	void saveOrUpdate(final User user);
	
	void delete(int id);
}
