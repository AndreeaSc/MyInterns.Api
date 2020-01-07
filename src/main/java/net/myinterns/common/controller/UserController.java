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
	public UserDTO getById(@PathParam("id") int id) {

		return userManager.getById(id);
	}

	@PUT
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(UserDTO user) {

		userManager.saveOrUpdate(user);

		return Response.ok(user).build();
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(UserDTO userDTO) {

		userManager.saveOrUpdate(userDTO);

		return Response.ok(userDTO).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") int id) {

		userManager.delete(id);

		return Response.ok(id).build();
	}

	@POST
	@Path("/login")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login(String user) {

		JSONObject jsonObj;

		try {
			jsonObj = new JSONObject(user);
			String username = jsonObj.getString("username");
			String password = jsonObj.getString("password");

			if (userManager.login(username, password) != null) {

				return Response.ok(username).build();
			} else
				return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for user: " + user).build();

		} catch (Exception e) {
			// TODO: handle exception
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for user: " + user).build();
		}
	}
}
