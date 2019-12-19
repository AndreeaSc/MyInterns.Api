package net.andreea.MyInterns.persistance.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.UserDTO;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.myinterns.business.PersistanceOperations;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> getAll() {
		final List<UserDTO> userList = sessionFactory.getCurrentSession().createCriteria(UserDTO.class).list();

		System.out.println("************ ALL USERs ****************");

		for (final UserDTO user : userList) {
			System.out.printf("Username:%s \t \n", user.getUsername().toString());
		}

		return userList;
	}

	@Override
	public UserDTO getById(long id) {

		UserDTO userDTO = null;

		Query q = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id=:id").setParameter("id", id);

		try {
			userDTO = (UserDTO) q.uniqueResult();
		} catch (Exception ex) {
			System.out.printf("Exception in getStudentbyId: %s \n", ex.getMessage());
		}

		return userDTO;
	}

	@Override
	public void delete(long id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserDTO user = (UserDTO) session.load(UserDTO.class, id);

		session.delete(user);

		session.getTransaction().commit();
	}

	@Override
	public void saveOrUpdate(String username, String password, Boolean isMentor) {

		final UserDTO user = new UserDTO(username, password, isMentor);
		saveOrUpdate(user);
	}

	@Override
	public void saveOrUpdate(String username, String password) {

		final UserDTO user = new UserDTO(username, password);
		saveOrUpdate(user);
	}
	
	@Override
	public void saveOrUpdate(UserDTO userDTO) {
		
		new PersistanceOperations().saveOrUpdate(sessionFactory, userDTO, "*** User '" + userDTO.getUsername() + "' saved!");
	}
}
