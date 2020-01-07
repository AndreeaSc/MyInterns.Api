package net.myinterns.persistance.dao;

import java.text.SimpleDateFormat;

import net.myinterns.persistance.entity.Holiday;
import net.myinterns.persistance.entity.Mentor;
import net.myinterns.persistance.entity.Student;

public interface HolidayDao {
	
	void saveOrUpdate(final SimpleDateFormat start_date, final SimpleDateFormat end_date, final Mentor mentor);

	void saveOrUpdate(final SimpleDateFormat start_date, final SimpleDateFormat end_date, final Student student);

	void saveOrUpdate(final Holiday holiday);

//	List<Holidays> getHolidaysList(final Mentor mentor);
}
	