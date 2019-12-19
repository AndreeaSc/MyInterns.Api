package net.myinterns.business;

import java.util.List;

public interface UserManager {
	List<UserManager> getAll();

	UserManager getById(long id);

	void saveOrUpdate(String username, String password, Boolean isMentor);

	void saveOrUpdate(String username, String password);

	void saveOrUpdate(final UserManager user);

	void delete(long id);
}
