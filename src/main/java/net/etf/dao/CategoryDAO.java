package net.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.etf.dto.Category;

public class CategoryDAO {

	public CategoryDAO() {
		// TODO Auto-generated constructor stub
	}

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String FIND_ALL_QUERY = "select * from category where status=true";
	private static final String INSERT_CATEGORY_QUERY = "insert into category(name,status) values(?, ?)";
	private static final String DELETE_CATEGORY_QUERY = "update category c set status=? where c.id=? ";
	private static final String UPDATE_CATEGORY_QUERY = "update category c set name=? where c.id=?";
	private static final String FIND_CATEGORY_NAME_QUERY = "SELECT name FROM category WHERE id = ?";

	public static List<Category> findAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Category> result = new ArrayList<>();
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, FIND_ALL_QUERY, false, new Object[0]);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(new Category(resultSet.getInt(1), resultSet.getString(2)));
			}

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static Category insert(Category request) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Category result = null;
		Object[] values = { request.getName(), true };
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

	public static boolean delete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object[] values = { false, id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, DELETE_CATEGORY_QUERY, false, values);
			result = statement.executeUpdate() == 1;
		} finally {
			connectionPool.checkIn(connection);

		}
		return result;

	}

	public static boolean update(Category category) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object[] values = { category.getName(), category.getId() };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, UPDATE_CATEGORY_QUERY, false, values);
			result = statement.executeUpdate() == 1;
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static String findCategoryNameByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String attributeName = null;

		try {
			connection = connectionPool.checkOut();
			statement = connection.prepareStatement(FIND_CATEGORY_NAME_QUERY);
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

}
