package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

public interface StudentDao {

	List<Student> getAll();

	Student getById(long id);

	void saveOrUpdate(final String name, final String surname, final String description, final String email, final User user);

	void saveOrUpdate(final Student student);

	void delete(long id);

	List<Student> displayStudent();
}
