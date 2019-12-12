import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andreea.MyInterns.persistance.dao.HolidaysDao;
import net.andreea.MyInterns.persistance.dao.MentorDao;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Holidays;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

public class Application {

	public static void main(String[] args) {

		final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		StudentDao studentDao = appContext.getBean(StudentDao.class);
		MentorDao mentorDao = appContext.getBean(MentorDao.class);
		UserDao userDao = appContext.getBean(UserDao.class);
			
		userDao.saveOrUpdate("blandiana@gmail.com", "root", true);
		
		userDao.getAll();		
	}
}
