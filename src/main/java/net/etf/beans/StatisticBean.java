package net.etf.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etf.dao.StatisticDAO;
import net.etf.dto.Statistic;


public class StatisticBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatisticBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Statistic> findAll() {
		List<Statistic> result = new ArrayList<>();

		try {
			result = StatisticDAO.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
