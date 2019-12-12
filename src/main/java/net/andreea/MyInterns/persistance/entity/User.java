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

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "isMentor")
	private Boolean isMentor;
	
	@OneToOne(mappedBy = "user", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, orphanRemoval = true, fetch = FetchType.LAZY)	
	private Mentor mentor;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsMentor() {
		return isMentor;
	}

	public void setIsMentor(Boolean isMentor) {
		this.isMentor = isMentor;
	}

	public User() {
	}

	public User(final String email, final String password, final Boolean isMentor) {
		this.email = email;
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

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(final Mentor mentor) {
		if (mentor == null) {
			if (this.mentor != null) {
				this.mentor.setUser(null);
			}
		} else {
			mentor.setUser(this);
		}
		this.mentor = mentor;
	}
}
