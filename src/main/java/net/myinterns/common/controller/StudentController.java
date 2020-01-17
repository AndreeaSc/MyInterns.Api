package net.myinterns.common.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.myinterns.business.StudentManager;
import net.myinterns.persistance.dao.StudentDao;
import net.myinterns.persistance.entity.Student;
import net.myinterns.persistance.entity.User;

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

		return studentDao.getAll();
	}

	@GET
	@Path("/getBy/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public StudentDTO getById(@PathParam("id") int id) {

		return studentManager.getById(id);
	}

	@POST
	@Path("/updateByEmail/{email}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Student update(@PathParam("email") String email, String student) {

		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(student);
			String name = jsonObj.getString("name");
			String surname = jsonObj.getString("surname");
			String description = jsonObj.getString("description");
			String email1 = jsonObj.getString("email");

			Student studentS = new Student();
			studentS.setName(name);
			studentS.setSurname(surname);
			studentS.setDescription(description);
			studentS.setEmail(email1);

			System.out.println("Student updated as: " + studentS.toString());

			return studentDao.updateByEmail(studentS, email);

		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("/addDTO")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addDTO(String studentUser) throws JSONException {
		System.out.println("Add user student dto controller");
		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(studentUser);
			String name = jsonObj.getString("name");
			String surname = jsonObj.getString("surname");
			String description = jsonObj.getString("description");
			String email = jsonObj.getString("email");
			String username = jsonObj.getString("username");
			String password = jsonObj.getString("password");

			User user = new User();
			user.setUsername(username);
			user.setPassword(password);

			Student student = new Student();
			student.setName(name);
			student.setSurname(surname);
			student.setDescription(description);
			student.setEmail(email);
			student.setUser(user);

			studentDao.saveOrUpdateDAO(student);

			return Response.ok(student).build();
		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(Student student) throws JSONException {

		studentDao.saveOrUpdateDAO(student);
		
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