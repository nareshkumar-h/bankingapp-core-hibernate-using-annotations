package com.naresh.bankingapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.naresh.bankingapp.dao.impl.UserDAOImpl;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.util.HibernateUtil;

public class TestUserGetLoad {

	static UserDAO userDAO = new UserDAOImpl();

	static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {

		int userId = 9;
		testLoad(userId);
		testGet(userId);
	}

	private static void testLoad(int userId) {
		System.out.println("Test Load");
		Session session = sf.openSession();
		User user = session.load(User.class, userId);
		//System.out.println(user);
		System.out.println(user.getId());
		session.close();
	}
	
	private static void testGet(int userId) {
		System.out.println("Test get");
		Session session = sf.openSession();
		User user = session.get(User.class, userId);
		System.out.println(user);
		session.close();
	}

}
