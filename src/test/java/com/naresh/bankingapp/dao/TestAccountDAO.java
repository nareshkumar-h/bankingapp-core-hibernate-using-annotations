package com.naresh.bankingapp.dao;

import java.util.List;

import com.naresh.bankingapp.dao.impl.AccountDAOImpl;
import com.naresh.bankingapp.dao.impl.UserDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;
import com.naresh.bankingapp.model.User;

public class TestAccountDAO {

	static AccountDAO accountDAO = new AccountDAOImpl();

	static UserDAO userDAO = new UserDAOImpl();

	public static void main(String[] args) {

		//createAccount();
		//createAccount2();

		 listAccounts();
		// updateUser();

		//deleteAccount();
	}

	private static void listAccounts() {
		try {
			List<Account> list = accountDAO.list();
			System.out.println("No of records:" + list.size());
			if (list != null) {
				for (Account account : list) {
					System.out.println(account);
				}
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	private static void createAccount() {

		int userId = 11;

		try {
			User user = userDAO.findOne(userId);

			Account account = new Account();
			account.setUser(user);
			account.setBalance(100);
			accountDAO.insert(account);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	private static void createAccount2() {

		try {
			User user = new User();
			user.setName("Naresh");
			user.setEmail("naresh@gmail.com");
			user.setPassword("pass123");
			Account account = new Account();
			account.setUser(user);
			account.setBalance(100);
			accountDAO.insert(account);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	private static void deleteAccount() {
		int accountId = 7;

		try {
			Account findOne = accountDAO.findOne(accountId);
			accountDAO.delete(findOne);
			System.out.println(findOne);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
