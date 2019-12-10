package net.andreea.MyInterns.comon.controller;

import net.andreea.MyInterns.persistance.dao.impl.MentorDaoImpl;
import net.andreea.MyInterns.persistance.entity.Mentor;
import net.andreea.MyInterns.persistance.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/mentors")
public class MentorController {

	MentorDaoImpl mentorDao = new MentorDaoImpl();

	@GET
	@Path("/work")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "functioneaz2";
	}

	@GET
	@Path("/show")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> getJSON() {
		
		List data = new ArrayList<String>();
		
		data.add("textul meu unul si numai unul pt ca functioneaza");
		
		return data;
	}
}