package net.andreea.MyInterns.comon.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

@Path("/user")
public class UserController {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao userDao = appContext.getBean(UserDao.class);
	
	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> getUsers() {
		
		return userDao.getAll();
	}
	
	@GET
	@Path("/getBy/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public User getById(@PathParam("id") int id) {
		
		User student = userDao.getById(id);
		System.out.println(student);

		return student;
	}
	
	@PUT
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(User user) {

		userDao.saveOrUpdate(user);

		return Response.ok(user).build();
	}	
	
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(User user) {

		userDao.saveOrUpdate(user);

		return Response.ok(user).build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response delete(@PathParam("id") int id) {

		userDao.delete(id);

		return Response.ok(id).build();
	}
	
	//many to many principal -> JsonManagedReference = chiar sub join table sau list
	// la celalalt -> JsonBackReference - la cea cu map
}
