import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andreea.MyInterns.persistance.dao.MentorDao;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.User;

public class Application {

	public static void main(String[] args) {

		final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		StudentDao studentDao = appContext.getBean(StudentDao.class);
		MentorDao mentorDao = appContext.getBean(MentorDao.class);
		UserDao userDao = appContext.getBean(UserDao.class);

//		userDao.saveOrUpdate("blandiana@gmail.com", "root", true);
//		
//		userDao.getAll();		

		List<User> users = new ArrayList<User>();

//		users = userDao.getAll();

//		for (User user : users) {
//			if (user.getIsMentor() == true)
//				System.out.println("Userul este mentor!");
//		}

//	    final User user1 = new User("dana@gmail.com", "root", true);
//	    userDao.saveOrUpdate(user1);
//	    final Mentor mentor1 = new Mentor("Dana", "Padure", user1);
//	    mentorDao.saveOrUpdateIfMentor(user1, mentor1);

		List<Mentor> mentors = new ArrayList<Mentor>();

		mentors = mentorDao.getAll();
		if (mentorDao.getById(5) != null) {
			System.out.println("yes!");
		}
//		if (mentor.getIsMentor() == true)
//			System.out.println("Userul este mentor!");

	}
}
