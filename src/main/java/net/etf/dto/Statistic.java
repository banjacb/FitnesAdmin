package net.etf.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Statistic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Timestamp time;
	private String description;

	
	public Statistic(int id, String name, Timestamp time, String description) {
		super();
		this.setId(id);
		this.name = name;
		this.time = time;
		this.description = description;
	}

	public String getStatus() {
		return name;
	}

	public void setStatus(String status) {
		this.name = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Statistic() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
