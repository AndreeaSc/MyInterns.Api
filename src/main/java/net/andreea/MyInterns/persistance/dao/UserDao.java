package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.User;

public interface UserDao {

	void saveOrUpdate(final String username, final String password, final Mentor mentor);

	void readAll();

	void delete();

	User getUser(final String username, final String password);

	List<User> getAll();
}
