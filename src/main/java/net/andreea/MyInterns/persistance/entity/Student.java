package net.andreea.MyInterns.persistance.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
	private Set<Mentor> mentors = new HashSet<>();

	public Student() {
	}

	public Student(final String firstname, final String description, final String lastname) {
		this.firstname = firstname;
		this.description = description;
		this.lastname = lastname;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Student)) {
			return false;
		}

		Student student = (Student) o;
		return Objects.equals(firstname, student.firstname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstname);
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	Set<Mentor> getMentors() {
		return mentors;
	}

	public void setMentors(final Set<Mentor> mentors) {
		this.mentors = mentors;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", description="
				+ description + ", mentors=" + mentors + "]";
	}
}