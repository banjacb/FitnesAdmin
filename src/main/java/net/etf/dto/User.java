package net.etf.dto;

import java.io.Serializable;

public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String city;
	private String email;
	private int id;
	private int role;
	private boolean status;

	
	public User(int id,String userName, String password, String firstName, String lastName, String city, String email,
			int role, boolean status) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.id = id;
		this.role = role;
		this.status = status;
	}


	public User(String userName, String password, String firstName, String lastName,String city,String email, int role,boolean status) {
		
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city=city;
		this.email=email;
		this.role=role;
		this.status=status;
		
		
	}



	public User(int id,String userName, String password, String firstName, String lastName, int role, boolean status) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.role=role;
		this.status=status;
		
		
	}

	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}

	
	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public String getUserName() {
		return userName;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}

