package net.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etf.dto.Attribute;

public class AttributeDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String FIND_ALL_QUERY = "select * from attribute_category where category_id=? and status=true";
	private static final String INSERT_CATEGORY_QUERY = "insert into attribute_category(name,status,category_id) values(?, ?, ?)";
	private static final String DELETE_ATTRIBUTE_QUERY = "DELETE FROM attribute_category WHERE id=?";
	private static final String UPDATE_ATTRIBUTE_QUERY = "update attribute_category c set name=? where c.id=?";
	private static final String FIND_ATTRIBUTE_NAME_QUERY = "SELECT name FROM attribute_category WHERE id = ?";

	public AttributeDAO() {
	}

	public static String findAttributeNameByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String attributeName = null;

		try {
			connection = connectionPool.checkOut();
			statement = connection.prepareStatement(FIND_ATTRIBUTE_NAME_QUERY);
			statement.setString(1, name);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				attributeName = resultSet.getString("name");
			}
		} finally {
			if (connection != null) {
				connectionPool.checkIn(connection);
			}
		}

		return attributeName;
	}

	public static List<Attribute> findAll(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Attribute> result = new ArrayList<>();
		Object[] values = { id };
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, FIND_ALL_QUERY, false, values);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(new Attribute(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
			}

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static Attribute insert(int id, Attribute request) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Attribute result = null;
		Object[] values = { request.getName(), true, id };
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, INSERT_CATEGORY_QUERY, true, values);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			result = request;
			if (resultSet.next())
				result.setId(resultSet.getInt(1));

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
			statement = connection.prepareStatement(DELETE_ATTRIBUTE_QUERY);
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

	public static boolean update(Attribute a) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object[] values = { a.getName(), a.getId() };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, UPDATE_ATTRIBUTE_QUERY, false, values);
			result = statement.executeUpdate() == 1;
		} finally {
			connectionPool.checkIn(connection);

		}
		return result;
	}

}
