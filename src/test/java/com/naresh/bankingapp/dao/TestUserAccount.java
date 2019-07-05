package com.naresh.bankingapp.dao;

import com.naresh.bankingapp.dao.impl.UserDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;

public class TestUserAccount {

	static UserDAO userDAO = new UserDAOImpl();

	public static void main(String[] args) {

		deleteUser();
	}

	private static void deleteUser() {
		int userId = 11;
		
		try {
			User user = userDAO.findOne(userId);
			userDAO.delete(user);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
