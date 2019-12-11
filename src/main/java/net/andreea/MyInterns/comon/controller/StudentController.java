package net.andreea.MyInterns.comon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.entity.Student;

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
	public List<Student> getList() {

		return studentDao.getAll();
	}

	@GET
	@Path("/student/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Student getById(@PathParam("id") int id) {
		
		Student student = studentDao.getById(id);
		System.out.println(student);

		return student;
	}

	@PUT
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Student student) {

		studentDao.saveOrUpdate(student);

		return Response.ok(student).build();
	}
}
