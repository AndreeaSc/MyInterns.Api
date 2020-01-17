package net.myinterns.persistance.dao;

import java.util.List;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.persistance.entity.User;

public interface UserDao {

	List<UserDTO> getAll();

	User getById(long id);

	User getByUsername(String username);

	User getUser(final String username, final String password);

	void saveOrUpdate(String username, String password, boolean isMentor);

	void saveOrUpdate(String username, String password);

	void saveOrUpdate(final User user);

	User update(User user, long id);
	
	User updateByUsername(User user, String username);

	void delete(long id);

	void deleteByUsername(String username);

	User loginCheck(String userName, String password);
	
	public void deletei(UserDTO userDTO);
}