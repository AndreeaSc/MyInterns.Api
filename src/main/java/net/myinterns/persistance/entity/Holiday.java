package net.myinterns.persistance.entity;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "start_date")
	private SimpleDateFormat start_date;

	@Column(name = "end_date")
	private SimpleDateFormat end_date;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mentor_id", referencedColumnName = "user_id")
	private Mentor mentor;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "student_id", referencedColumnName = "user_id")
	private Student student;
	
	public Holiday() {
	}

	public Holiday(final SimpleDateFormat start_date, final SimpleDateFormat end_date, final Mentor mentor) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.mentor = mentor;
	}
	
	public Holiday(final SimpleDateFormat start_date, final SimpleDateFormat end_date, final Student student) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public SimpleDateFormat getStart_date() {
		return start_date;
	}

	public void setStart_date(SimpleDateFormat start_date) {
		this.start_date = start_date;
	}

	public SimpleDateFormat getEnd_date() {
		return end_date;
	}

	public void setEnd_date(SimpleDateFormat end_date) {
		this.end_date = end_date;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(final Mentor mentor) {
		this.mentor = mentor;
	}
}
