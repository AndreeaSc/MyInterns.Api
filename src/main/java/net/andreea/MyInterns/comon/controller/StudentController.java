package net.andreea.MyInterns.comon.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.dao.UserDao;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

@Path("/student")
public class StudentController {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	StudentDao studentDao = appContext.getBean(StudentDao.class);

	@GET
	@Path("/work")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "functioneaz2";
	}

	@GET
	@Path("/students")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Student> getUsers() {

		return studentDao.getAll();
	}
}
