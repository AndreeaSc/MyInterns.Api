package net.andreea.MyInterns.comon.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.User;

@Path("/user")
public class UserController {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao userDao = appContext.getBean(UserDao.class);
	
	@GET
	@Path("/work")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "functioneaz2";
	}
	
//	@GET
//	@Path("/users")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public List<User> getUsers() {
//		
//		return userDao.getAll();
//	}
	
	//many to many principal -> JsonManagedReference = chiar sub join table sau list
	// la celalalt -> JsonBackReference - la cea cu map
}
