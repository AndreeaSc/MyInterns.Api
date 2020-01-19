package net.myinterns.common.controller;

import javax.ws.rs.Path;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.myinterns.persistance.dao.MentorDao;

@Path("/group")
public class Group {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	MentorDao mentorDao = appContext.getBean(MentorDao.class);

//	@GET
//	@Path("/studentsOf/{mentor_id}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public List<Student> getList() {
//
//		return mentorDao.getAll();
//	}
}
