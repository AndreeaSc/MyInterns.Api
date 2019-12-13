package net.andreea.MyInterns.comon.controller;

import net.andreea.MyInterns.persistance.dao.MentorDao;
import net.andreea.MyInterns.persistance.dao.impl.MentorDaoImpl;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.Student;
import net.andreea.MyInterns.persistance.entity.User;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/mentor")
public class MentorController {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	MentorDao mentorDao = appContext.getBean(MentorDao.class);

	@GET
	@Path("/mentors")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Mentor> getList() {

		return mentorDao.getAllMentors();
	}
	
	@GET
	@Path("/getBy/{user_id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Mentor getById(@PathParam("user_id") int id) {
		
		Mentor mentor = mentorDao.getById(id);
		System.out.println(mentor);

		return mentor;
	}
	
	@PUT
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Mentor mentor) {

		mentorDao.saveOrUpdate(mentor);

		return Response.ok(mentor).build();
	}
	
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(Mentor mentor) {

		mentorDao.saveOrUpdate(mentor);

		return Response.ok(mentor).build();
	}
}