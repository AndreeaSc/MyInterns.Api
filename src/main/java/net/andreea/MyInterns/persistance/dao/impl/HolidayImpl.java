package net.andreea.MyInterns.persistance.dao.impl;

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.myinterns.business.PersistanceOperations;
import net.andreea.MyInterns.persistance.dao.HolidayDao;
import net.andreea.MyInterns.persistance.entity.Holiday;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;

@Repository
@Transactional
public class HolidayImpl implements HolidayDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(SimpleDateFormat start_date, SimpleDateFormat end_date, Mentor mentor) {
		final Holiday holiday = new Holiday(start_date, end_date, mentor);
		saveOrUpdate(holiday);
	}

	@Override
	public void saveOrUpdate(SimpleDateFormat start_date, SimpleDateFormat end_date, Student student) {
		final Holiday holiday = new Holiday(start_date, end_date, student);
		saveOrUpdate(holiday);
	}

	@Override
	public void saveOrUpdate(Holiday holiday) {
		new PersistanceOperations().saveOrUpdate(sessionFactory, holiday,
		"*** Holiday '" + holiday.getId() + "' saved!");
	}
}
