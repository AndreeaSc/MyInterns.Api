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

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.UserManager;
import net.myinterns.persistance.dao.UserDao;
import net.myinterns.persistance.entity.User;

@Path("/user")
public class UserController {

	final ApplicationContext appContextDTO = new ClassPathXmlApplicationContext("applicationContextDto.xml");
	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao userDao = appContext.getBean(UserDao.class);

	@Autowired
	private transient UserManager userManager = appContextDTO.getBean(UserManager.class);

	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserDTO> getUsers() {
		return userManager.getAll();
	}

	@GET
	@Path("/getBy/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDTO getById(@PathParam("id") long id) {

		return userManager.getById(id);
	}

	@GET
	@Path("/getByUsername/{username}")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDTO getById(@PathParam("username") String username) {

		return userManager.getByUsername(username);
	}

	@GET
	@Path("/getIdByUsername/{username}")
	@Produces({ MediaType.APPLICATION_JSON })
	public long getIdByUsername(@PathParam("username") String username) {

		UserDTO userDTO = new UserDTO();
		userDTO = userManager.getByUsername(username);

		long id = userDTO.getId();
		return id;
	}

	@POST
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserDTO update(@PathParam("id") long id, String user) {

		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(user);
			String username = jsonObj.getString("username");
			String password = jsonObj.getString("password");

			UserDTO userDTO = new UserDTO();
			userDTO.setPassword(password);
			userDTO.setUsername(username);
			userDTO.setId(id);

			return userManager.update(userDTO, id);

		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("/updateByUsername/{username}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserDTO update(@PathParam("username") String username, String user) {

		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(user);
			String usernameUser = jsonObj.getString("username");
			String passwordUser = jsonObj.getString("password");
			boolean isMentorUser = jsonObj.getBoolean("isMentor");

			UserDTO userDTO = new UserDTO();
			userDTO.setIsMentor(isMentorUser);
			userDTO.setPassword(passwordUser);
			userDTO.setUsername(usernameUser);

			return userManager.updateByUsername(userDTO, username);

		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDTO add(String user) {

		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(user);
			String username = jsonObj.getString("username");
			String password = jsonObj.getString("password");
			boolean isMentor = jsonObj.getBoolean("isMentor");

			return userManager.saveOrUpdate(username, password, isMentor);

		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") long id) {

		userManager.delete(id);

		return Response.ok(id).build();
	}
	
	@GET
	@Path("/delete/user/{username}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteByUsername(@PathParam("username") String username) {

		userManager.deleteByUsername(username);

		return Response.ok(username).build();
	}
	
	@POST
	@Path("/login")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserDTO login(String user) {

		UserDTO userDTO = new UserDTO();

		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(user);
			String username = jsonObj.getString("username");
			String password = jsonObj.getString("password");

			return userManager.login(username, password);

		} catch (Exception e) {
			return userDTO;
		}
	}
}