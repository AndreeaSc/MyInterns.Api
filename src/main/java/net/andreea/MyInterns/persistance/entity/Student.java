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

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
	private Set<Mentor> mentors = new HashSet<>();

	public Student() {
	}

	public Student(final String name, final String description) {
		this.name = name;
		this.description = description;
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
		return Objects.equals(name, student.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String student) {
		this.name = student;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", description=" + description + ", mentors="
				+ mentors + "]";
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
}