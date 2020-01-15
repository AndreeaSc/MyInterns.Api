package net.myinterns.common.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.myinterns.business.StudentManager;
import net.myinterns.persistance.dao.StudentDao;
import net.myinterns.persistance.entity.Student;

@Path("/student")
public class StudentController {

	final ApplicationContext appContextDTO = new ClassPathXmlApplicationContext("applicationContextDto.xml");
	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	StudentDao studentDao = appContext.getBean(StudentDao.class);

	@Autowired
	private transient StudentManager studentManager = appContextDTO.getBean(StudentManager.class);

	@GET
	@Path("/students")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<StudentDTO> getList() {

//		return studentDao.getAll();
	
		return null;
	}

	@GET
	@Path("/getBy/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public StudentDTO getById(@PathParam("id") int id) {

//		StudentDTO student = studentDao.getById(id);
//		System.out.println(student);

//		return student;
		
		return null;
	}

	@PUT
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(StudentDTO student) {

//		studentDao.saveOrUpdate(student);

		return Response.ok(student).build();
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(Student student) {
//
//		JSONObject jsonObj;
//
//		try {
//			jsonObj = new JSONObject(student);
//			String name = jsonObj.getString("name");
//			String surname = jsonObj.getString("surname");
//			String description = jsonObj.getString("description");
//			String email = jsonObj.getString("email");
//			
//			StudentDTO studentDTO = new StudentDTO();
//			
//			studentDTO.setName(name);
//			studentDTO.setSurname(surname);
//			studentDTO.setDescription(description);
//			studentDTO.setEmail(email);
//
//
//			return studentManager.saveOrUpdate(studentDTO);
//
//		} catch (Exception e) {
//			return null;
//		}
		
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