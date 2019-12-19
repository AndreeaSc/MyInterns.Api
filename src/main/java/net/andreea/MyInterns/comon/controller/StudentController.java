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

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andreea.MyInterns.persistance.dao.StudentDao;
import net.andreea.MyInterns.persistance.entity.Student;

@Path("/student")
public class StudentController {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	StudentDao studentDao = appContext.getBean(StudentDao.class);

	@GET
	@Path("/students")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<StudentDTO> getList() {

		return studentDao.getAll();
	}

	@GET
	@Path("/getBy/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public StudentDTO getById(@PathParam("id") int id) {

		StudentDTO student = studentDao.getById(id);
		System.out.println(student);

		return student;
	}

	@PUT
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(StudentDTO student) {

		studentDao.saveOrUpdate(student);

		return Response.ok(student).build();
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(StudentDTO student) {

		studentDao.saveOrUpdate(student);

		return Response.ok(student).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") int id) {

		studentDao.delete(id);

		return Response.ok(id).build();
	}
}