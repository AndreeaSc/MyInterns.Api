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

//		final UserDao userDao = appContext.getBean(UserDao.class);
//		final MentorDao mentorDao = appContext.getBean(MentorDao.class);
//		final StudentDao studentDao = appContext.getBean(StudentDao.class);
//		final HolidaysDao holidaysDao = appContext.getBean(HolidaysDao.class);
//
//		final User user1 = new User("aaa", "sss");
//		final Mentor mentor1 = new Mentor("Ionel", "Padure", user1);
//		final Mentor mentor2 = new Mentor("Viorel", "Dura", new User("ddd", "fff"));
//		final Holidays holidays1 = new Holidays("Sibiu", "Strada Lunga nr 12", mentor1);
//		final Student studentJDBC = new Student("JDBC", "Java DataBase Conectivity");
//		final Student studentHibernate = new Student("Hibernate", "Winter is comming ...");

//		System.out.println("************************  START  ************************");
//
//		mentorDao.saveOrUpdate(mentor1);
//		mentorDao.saveOrUpdate(mentor2);
//
//		holidaysDao.saveOrUpdate(holidays1);
//		holidaysDao.saveOrUpdate("Cluj", "Str Louis Pasteur 23/32", mentor1);
//
//		studentDao.saveOrUpdate("Java", "Java programming language");
//		studentDao.saveOrUpdate("Maven", "Build tool");
//		studentDao.saveOrUpdate(studentJDBC);
//		studentDao.saveOrUpdate(studentHibernate);
//
//		mentor1.addStudent(studentJDBC);
//		mentor1.addStudent(studentDao.getStudent("Java"));
//
//		mentor2.addStudent(studentJDBC);
//		mentor2.addStudent(studentDao.getStudent("Java"));
//		mentor2.addStudent(studentDao.getStudent("Maven"));
//
//		mentorDao.saveOrUpdate(mentor1);
//		mentorDao.saveOrUpdate(mentor2);
//
//		mentorDao.readAll();
//		userDao.readAll();
//
//		userDao.getUser("aaa", "wrong password");
//
//		final User user = userDao.getUser("aaa", "sss");
//
//		mentorDao.getMentor(null);
//		mentorDao.getMentor(new User("qwe", "qwe"));
//
//		final Mentor mentor3 = mentorDao.getMentor(user);
//
//		System.out.printf("Searched mentor1 is: %s %s and the holidays are: \n", mentor3.getFirstName(),
//				mentor3.getLastName());
//
//		for (final Holidays holidays : holidaysDao.getHolidaysList(mentor3)) {
//			System.out.printf("City:%s Street:%s \n", holidays.getCity(), holidays.getStreet());
//		}
//
//		final Set<Student> studentSet = studentDao.getMentorStudents(mentor2);
//
//		System.out.println("'" + mentor2.getFirstName() + "' skills are: ");
//		for (final Student student : studentSet) {
//			System.out.printf("Skill: '%s' Description: '%s' \n", student.getFirstName(), student.getDescription());
//		}

		System.out.println("************************  STOP  ************************");
	}
}
