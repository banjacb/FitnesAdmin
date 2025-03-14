package net.etf.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etf.dao.UserDAO;
import net.etf.dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private boolean isLoggedIn = false;

	public boolean login(String username, String password) {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		user = new User();
		isLoggedIn = false;
	}

	public User getUser() {
		return user;
	}

	public boolean isUserNameAllowed(String username) {
		return UserDAO.isUserNameUsed(username);
	}

	public  boolean isAdmin(String username) {
		int role = UserDAO.getUserRole(username);
		return role == 0;
	}

	public boolean blokUser(int id, boolean status) {

		return UserDAO.updateStatus(id, false);

	}

	public boolean unblock(int id) {
		return UserDAO.updateStatus(id, true);

	}

	public List<User> findAll() {
		List<User> result = new ArrayList<>();

		try {
			result = UserDAO.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean insert(User u) {
		try {
			

			return UserDAO.insertUser(u);
		} catch (Exception e) {
			return false;
		}
	}

	public  boolean block(int id) {
		return UserDAO.updateStatus(id, false);

	}

	public boolean deleteUser(int id) {
		return UserDAO.delete(id);
	}
	
	public User getUserById(Integer id) {

		return UserDAO.getUserById(id);

	}
	
	public boolean editUser(Integer id, User u) {
		return UserDAO.updateUser(id, u);
	}
}
