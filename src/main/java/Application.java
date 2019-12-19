import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.UserManager;

public class Application {

	public static void main(String[] args) {

		final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		final ApplicationContext appContextDTO = new ClassPathXmlApplicationContext("applicationContextDto.xml");
		
//		UserDao userDao = appContext.getBean(UserDao.class);
//		StudentDao studentDao = appContext.getBean(StudentDao.class);
		
//		StudentManager studentManager = appContext.getBean(StudentManager.class);
		UserManager userManager = appContextDTO.getBean(UserManager.class);
		
		final UserDTO userDTO = new UserDTO("vioricaS", "passw20rd");
		
//		userManager.getAll();
//		userManager.saveOrUpdate(userDTO);
//		StudentDTO studentDTO = new StudentDTO("marina", "balerina", "des", "email", userDTO);
//		
//		final UserDTO userDTO1 = new UserDTO("traian", "passw20rd");
//		userManager.saveOrUpdate(userDTO1);
//		StudentDTO studentDTO1 = new StudentDTO("petru", "andrei", "des", "email", userDTO1);
//
//		
//		List<StudentDTO> students = new ArrayList<>();
//		students.add(studentDTO);
//		students.add(studentDTO1);
		
		System.out.println(userDTO.getPassword());
		
		System.out.println("The list: ");
	}
}
