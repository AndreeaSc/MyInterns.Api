package net.myinterns.persistance.dao;

import java.util.List;

import net.myinterns.persistance.entity.Mentor;
import net.myinterns.persistance.entity.User;

public interface MentorDao {
	
	List<Mentor> getAll();
	
	Mentor getById(int id);
	
	void saveOrUpdate(final String name, final String surname, final String email, final String qualification, final Boolean isExternal, final User user);

	void saveOrUpdate(final Mentor mentor);

	void delete(long id);

//	List<Mentor> getAllMentors();
}
