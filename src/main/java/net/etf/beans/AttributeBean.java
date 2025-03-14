package net.etf.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etf.dao.AttributeDAO;
import net.etf.dto.Attribute;

public class AttributeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AttributeBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Attribute> findAll(int id) {
		List<Attribute> result = new ArrayList<>();
		try {
			result = AttributeDAO.findAll(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String findAttribute(String id) {
		String result = "";
		try {
			result = AttributeDAO.findAttributeNameByName(id);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return result;
	}

	public Attribute insert(int id, Attribute request) {
		try {
			return AttributeDAO.insert(id, request);
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean delete(int id) {
		
			return AttributeDAO.delete(id);
		
	}

	public boolean update(Attribute a) {
		try {
			return AttributeDAO.update(a);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
