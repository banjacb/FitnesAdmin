package net.etf.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.etf.dao.CategoryDAO;
import net.etf.dto.Category;

public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Category> findAll() {
		List<Category> result = new ArrayList<>();
		try {
			result = CategoryDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Category insert(Category request) {
		try {
			return CategoryDAO.insert(request);
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean delete(int id) {
		try {
			return CategoryDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Category request) {
		try {
			return CategoryDAO.update(request);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String findCategory(String id) {
		String result = "";
		try {
			result = CategoryDAO.findCategoryNameByName(id);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return result;
	}

}
