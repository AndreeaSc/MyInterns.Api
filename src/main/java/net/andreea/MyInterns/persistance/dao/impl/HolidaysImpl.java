package net.andreea.MyInterns.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andreea.MyInterns.comon.PersistenceOperations;
import net.andreea.MyInterns.persistance.dao.HolidaysDao;
import net.andreea.MyInterns.persistance.entity.Holidays;
import net.andreea.MyInterns.persistance.entity.Mentor;

@Repository
@Transactional
public class HolidaysImpl implements HolidaysDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(String city, String street, Mentor mentor) {
		final Holidays holidays = new Holidays(city, street, mentor);
		saveOrUpdate(holidays);
	}

	@Override
	public void saveOrUpdate(Holidays holidays) {
		new PersistenceOperations().saveOrUpdate(sessionFactory, holidays,
				"*** Holidays '" + holidays.getCity() + "' saved!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Holidays> getHolidaysList(Mentor mentor) {
		List<Holidays> holidaysList = new ArrayList<>();

		final Query q = sessionFactory.getCurrentSession().createQuery("FROM Holidays WHERE mentor=:mentor");
		q.setParameter("mentor", mentor);

		try {
			holidaysList = (List<Holidays>) q.list();
			if (holidaysList == null) {
				System.out.println("Holidays Not Found !");
			}
		} catch (Exception ex) {
			System.out.printf("Exception in getHolidaysLIst: %s \n", ex.getMessage());
		}

		return holidaysList;
	}
}
