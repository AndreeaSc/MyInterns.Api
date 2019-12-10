package net.andreea.MyInterns.comon.controller;

import net.andreea.MyInterns.persistance.dao.MentorDao;
import net.andreea.MyInterns.persistance.dao.impl.MentorDaoImpl;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	@Path("/work")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "functioneaz2";
	}

//	@GET
//	@Path("/mentors")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public List<Mentor> getJSON() {
//
//		return mentorDao.getAllMentors();
//	}
}