package net.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etf.dto.Statistic;

public class StatisticDAO {

	private static final String FIND_ALL= "SELECT * FROM statistic";
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	public StatisticDAO() {
		// TODO Auto-generated constructor stub
	}

	public static List<Statistic> findAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Statistic> result = new ArrayList<>();
		try {
			connection = connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, FIND_ALL, false, new Object[0]);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(new Statistic(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getTimestamp("date"), resultSet.getString("description")));
			}

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
}
