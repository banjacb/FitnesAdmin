package net.etf.dto;

import java.io.Serializable;

public class Attribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Attribute() {
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String name;
	private int c_id;
	private boolean status;

	public Attribute(int id, String name, int c_id) {
		super();
		this.id = id;
		this.name = name;
		this.c_id = c_id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public Attribute(String name, int id) {
		this.name = name;
		this.id = id;
		status = true;
		c_id = this.getC_id();
	}

}
