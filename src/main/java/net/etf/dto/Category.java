package net.etf.dto;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private String name;

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	
	
	

}
