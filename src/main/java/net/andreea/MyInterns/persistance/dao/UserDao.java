package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

public interface UserDao {

	void readAll();

	void delete(long id);

	User getUser(final String username, final String password);

	List<User> getAll();

	void saveOrUpdate(String username, String password, Boolean isMentor);

	User getById(long id);

	void saveOrUpdate(final User user);
}
