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

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "mentor")
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "qualification")
	private String qualification;
	
	@Column(name = "isExternal")
	private Boolean isExternal;

	@OneToOne(fetch = FetchType.LAZY)  
	@MapsId
	private User user;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(name = "mentors_students", joinColumns = @JoinColumn(name = "mentor_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonManagedReference
	private Set<Student> students = new LinkedHashSet<>();
	
	public Mentor() {
	}

	public Mentor(String name, String surname, String email, String qualification, Boolean isExternal, User user) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.qualification = qualification;
		this.isExternal = isExternal;
		this.user = user;
	}

	public void addStudent(Student student) {
		System.out.printf("Adding student '%s' to '%s' \n", student.getName(), this.name);
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

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Boolean getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}

	public User getUser() {
		return user;
	}

	void setUser(final User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Mentor [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", qualification="
				+ qualification + ", isExternal=" + isExternal + ", user=" + user + ", students=" + students.size() + "]";
	}	
}