package net.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etf.dto.User;

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
	private static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username = ?";
	private static final String SQL_INSERT = "INSERT INTO user (username, password, first_name, last_name, city, email, role, status) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_GET_USER_ROLE = "SELECT role FROM user WHERE username = ?";
	private static final String UPDATE_USER_QUERY = "update user c set status=? where c.id=?";
	private static final String FIND_ALL_QUERY = "select * from user";
	private static final String DELETE_USER_QUERY = "delete from user where id=?";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String UPDATE_USER = "UPDATE user SET username = ?, password = ?, first_name = ?, last_name = ?,city = ?, email = ?, role = ?, status = ? WHERE id = ?";


	public static User selectByUsernameAndPassword(String username, String password) {
		User user = null;
		Connection connection = null;
		ResultSet resultSet = null;
		Object values[] = { username, password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD, false,
					values);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("city"), resultSet.getString("email"),
						resultSet.getInt("role"), resultSet.getBoolean("status"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static boolean isUserNameUsed(String username) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_USERNAME_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean insertUser(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		
		Object values[] = { user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
				user.getCity(), user.getEmail(), user.getRole(), true };
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, SQL_INSERT, false, values);
			result = statement.executeUpdate() == 1;
		} finally {
			connectionPool.checkIn(connection);

		}
		return result;
	}

	public static int getUserRole(String username) {
		int role = -1;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_USER_ROLE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				role = rs.getInt("role");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return role;
	}

	public static boolean updateStatus(int id, boolean status) {
		Connection connection = null;
		PreparedStatement statement = null;
		Object[] values = { status, id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, UPDATE_USER_QUERY, false, values);
			result = statement.executeUpdate() == 1;
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);

		}
		return result;
	}

	public static List<User> findAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<User> result = new ArrayList<>();
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, FIND_ALL_QUERY, false, new Object[0]);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("city"), resultSet.getString("email"),
						resultSet.getInt("role"), resultSet.getBoolean("status")));
			}

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			connection = connectionPool.checkOut();
			statement = connection.prepareStatement(DELETE_USER_QUERY);
			statement.setInt(1, id);

			result = statement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connectionPool.checkIn(connection);
			}
		}

		return result;
	}

public static User getUserById(Integer id) {
		
		User res = null;
		
		Connection connection = null;
		ResultSet resultSet = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();

			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_USER_BY_ID, false, values);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()){
				
			res = new User(resultSet.getInt("id"), resultSet.getString("username"),
					resultSet.getString("password"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getString("city"), resultSet.getString("email"),
					resultSet.getInt("role"), resultSet.getBoolean("status"));
			}

			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;

	}

public static boolean updateUser(Integer id, User newUser) {
    
	boolean res = false;
    Connection connection = null;

    
    try {
    
        connection = connectionPool.checkOut();
        PreparedStatement pstmt = DAOUtil.prepareStatement(connection, UPDATE_USER,true);
        
        pstmt.setString(1, newUser.getUsername());
        pstmt.setString(2, newUser.getPassword());
        pstmt.setString(3, newUser.getFirstName());
        pstmt.setString(4, newUser.getLastName());
        pstmt.setString(5, newUser.getCity());
        pstmt.setString(6, newUser.getEmail());
        pstmt.setInt(7, newUser.getRole());
        pstmt.setBoolean(8, newUser.isStatus());
        pstmt.setInt(9, id); 
        
         pstmt.executeUpdate() ; 
         
			if (pstmt.getUpdateCount() > 0) {
				res = true;
			}
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        connectionPool.checkIn(connection);
    }
    
    return res;
}

	
}
