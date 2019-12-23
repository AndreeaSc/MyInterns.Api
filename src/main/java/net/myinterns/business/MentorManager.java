package net.myinterns.business;

import java.util.List;

import net.myinterns.persistance.entity.User;

public interface MentorManager {
	
	List<MentorManager> getAll();

	MentorManager getById(int id);

	void saveOrUpdate(final String name, final String surname, final String email, final String qualification,
			final Boolean isExternal, final User user);

	void saveOrUpdate(final MentorManager mentor);

	void delete(long id);

//	List<Mentor> getAllMentors();
}
