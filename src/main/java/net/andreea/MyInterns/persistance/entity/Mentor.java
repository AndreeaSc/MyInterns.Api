package net.andreea.MyInterns.persistance.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mentor")
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "mentor_student_matrix", joinColumns = @JoinColumn(name = "mentor_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Student> students = new LinkedHashSet<>();

	public Mentor() {
	}

	public Mentor(final String firstName, final String lastName, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
	}

	public void addStudent(Student student) {
		System.out.printf("Adding student '%s' to '%s' \n", student.getStudentName(), this.firstName);
		students.add(student);
		student.getMentors().add(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Mentor)) {
			return false;
		}

		return id != null && id.equals(((Mentor) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public User getUser() {
		return user;
	}

	void setUser(final User user) {
		this.user = user;
	}
}
