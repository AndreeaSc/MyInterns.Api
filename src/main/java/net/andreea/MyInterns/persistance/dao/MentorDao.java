package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

public interface MentorDao {

	void saveOrUpdate(final Mentor mentor);

	void readAll();

	Mentor getMentor(final User user);

	List<Mentor> getAllMentors();

	Mentor getById(int id);

	void deleteMentor(int id);

	List<Mentor> getAll();
}
