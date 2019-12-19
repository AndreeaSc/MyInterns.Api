package net.andreea.MyInterns.persistance.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "isMentor")
	private Boolean isMentor;

	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Student student;

	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Mentor mentor;

	public User() {	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, Boolean isMentor) {
		this.username = username;
		this.password = password;
		this.isMentor = isMentor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Boolean getIsMentor() {
		return isMentor;
	}

	public void setIsMentor(Boolean isMentor) {
		this.isMentor = isMentor;
	}

	public void setStudent(final Student student) {
		if (student == null) {
			if (this.student != null) {
				this.student.setUser(null);
			}
		} else {
			student.setUser(this);
		}
		this.student = student;
	}

	public void setMentor(Mentor mentor) {
		if (mentor == null) {
			if (this.mentor != null) {
				this.mentor.setUser(null);
			}
		} else {
			mentor.setUser(this);
		}
		this.mentor = mentor;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isMentor=" + isMentor;
	}
}
