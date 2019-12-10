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

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "students")
	private Set<Mentor> mentors = new HashSet<>();

	public Student() {
	}

	public Student(final String studentName, final String description) {
		this.studentName = studentName;
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
		return Objects.equals(studentName, student.studentName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentName);
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudent(final String student) {
		this.studentName = student;
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