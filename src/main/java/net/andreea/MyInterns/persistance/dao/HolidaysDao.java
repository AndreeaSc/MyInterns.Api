package net.andreea.MyInterns.persistance.dao;

import java.util.List;

import net.andreea.MyInterns.persistance.entity.Holidays;
import net.andreea.MyInterns.persistance.entity.Mentor;

public interface HolidaysDao {
	
	void saveOrUpdate(final String city, final String street, final Mentor mentor);

	void saveOrUpdate(final Holidays address);

	List<Holidays> getHolidaysList(final Mentor mentor);
}
