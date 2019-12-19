import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

public class Application {

	public static void main(String[] args) {

		final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao userDao = appContext.getBean(UserDao.class);
		StudentDao studentDao = appContext.getBean(StudentDao.class);
		
		final User user = new User("andreea98", "passw20rd");
		userDao.saveOrUpdate(user);		
		Student student = new Student("sch", "and", "des", "email", user);
		
		final User user1 = new User("roxana", "passw20rd");
		userDao.saveOrUpdate(user);		
		Student student1 = new Student("schiau", "andreea", "des", "email", user);
		
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student);
				
		StudentDTO studentDTO = new StudentDTO(student.getName());
		System.out.println("DTO:" + studentDTO.getName());	
		
		System.out.println("The list: " + students);		
	}
}
