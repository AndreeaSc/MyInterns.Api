package net.andreea.MyInterns.persistance.dao;

import java.text.SimpleDateFormat;

import net.andreea.MyInterns.persistance.entity.Holiday;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;

public interface HolidayDao {
	
	void saveOrUpdate(final SimpleDateFormat start_date, final SimpleDateFormat end_date, final Mentor mentor);

	void saveOrUpdate(final SimpleDateFormat start_date, final SimpleDateFormat end_date, final Student student);

	void saveOrUpdate(final Holiday holiday);

//	List<Holidays> getHolidaysList(final Mentor mentor);
}
	