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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
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

//		studentDao.saveOrUpdate(student);

//		return Response.ok(student).build();
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(Student student) throws JSONException {

		studentDao.saveOrUpdateDAO(student);
		return Response.ok(student).build();

//		JSONObject jsonObj, jsonObjUsername;
//
//		jsonObj = new JSONObject(student);
//		jsonObjUsername = jsonObj.getJSONObject("user");
////		String jsonObjUsername = jsonObj.getJSONObject("user").getString("username");
////		String jsonObjPassword = jsonObj.getJSONObject("user").getString("password");
//		UserDTO userDTO = new UserDTO();
//		String jsonUsername = jsonObjUsername.getString("username");
////		userDTO.setUsername(jsonObjUsername);
////		userDTO.setPassword(jsonObjPassword);
//		
//		userDTO.setUsername("andreeaFake");
//		userDTO.setPassword("parolaFake");
//		
//		try {
//			jsonObj = new JSONObject(student);
//			String name = jsonObj.getString("name");
//			String surname = jsonObj.getString("surname");
//			String description = jsonObj.getString("description");
//			String email = jsonObj.getString("email");
//
//			return studentManager.saveOrUpdate(name, surname, description, email, userDTO);
//
//		} catch (Exception e) {
//			return null;
//		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") int id) {

		studentDao.delete(id);

		return Response.ok(id).build();
	}
}