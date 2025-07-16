package com.example.TaskScheduler.models;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="tasks")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String task_name;
	@Column(nullable=false)
	private String task_type; // Personal and  Professional
	@Column(nullable=false)
	private Date entry_date;
	@Column(nullable=false)
	private Date deadline_date;
	@Column(nullable=false)
	private int estimated_time;
	
	public Client() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public Date getDeadline_date() {
		return deadline_date;
	}
	public void setDeadline_date(Date deadline_date) {
		this.deadline_date = deadline_date;
	}
	public int getEstimated_time() {
		return estimated_time;
	}
	public void setEstimated_time(int estimated_time) {
		this.estimated_time = estimated_time;
	}
}
