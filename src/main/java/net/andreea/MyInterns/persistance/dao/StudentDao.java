package net.andreea.MyInterns.persistance.dao;

import java.util.List;
import java.util.Set;

import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

public interface StudentDao {

	void saveOrUpdate(final String firstName, final String lastName, final String description);

	void saveOrUpdate(final Student student);

	Student getStudent(final String studentName);

	Set<Student> getMentorStudents(final Mentor mentor);

	List<Student> getAll();

	Student getById(long id);
	
	void deleteStudent(long id);
}
