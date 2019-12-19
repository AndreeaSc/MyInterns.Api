package net.myinterns.business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersistanceOperations {
	public void saveOrUpdate(final SessionFactory sessionFactory, final Object object, final String message) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(object);
		session.getTransaction().commit();
		System.out.println(message);
		session.close();
	}
}
