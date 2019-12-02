package net.andreea.MyInterns.persistance.dao;

import java.util.Set;

import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;

public interface StudentDao {

	void saveOrUpdate(final String studentName, final String description);

	void saveOrUpdate(final Student student);

	Student getStudent(final String studentName);

	Set<Student> getMentorStudents(final Mentor mentor);
}
