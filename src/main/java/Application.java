import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.UserManager;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		final ApplicationContext appContextDTO = new ClassPathXmlApplicationContext("applicationContextDto.xml");

		UserManager userManager = appContextDTO.getBean(UserManager.class);

		
		final UserDTO userDTO = new UserDTO("veronica", "passw20rdMicle", true);
		userManager.saveOrUpdate("veronica", "passw20rdMicle", true);
		
				
		System.out.println("test");        
		
		
		List<UserDTO> usersDTOList = new ArrayList<>();
		usersDTOList = userManager.getAll();
		
//		UserDTO userDTO = userManager.getById(1);
//		System.out.println(userDTO.toString());
		
		
		System.out.println(usersDTOList.toString());
		
		

//		for (UserDTO user : usersDTOList) {
//			System.out.println(user.getUsername() + " ");
//		}

//		List<UserDTO> usersDTOListTest = new ArrayList<>(); 
//		usersDTOListTest = userManager.getAll();
//		
//		
//		for (UserDTO user : usersDTOListTest) {
//			System.out.println(user.toString() + " ");
//		}
//		
	}
}
