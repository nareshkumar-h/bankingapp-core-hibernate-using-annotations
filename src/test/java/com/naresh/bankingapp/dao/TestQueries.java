package com.naresh.bankingapp.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.naresh.bankingapp.dao.impl.UserDAOImpl;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.util.HibernateUtil;

public class TestQueries {

	static UserDAO userDAO = new UserDAOImpl();

	static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {

		testHQLQuery();
		testNativeQuery();
		testCriteriaQuery("ravi");
		testNamedQuery();
	}

	private static void testNamedQuery() {
		System.out.println("Named Query");
		Session session = sf.openSession();
		List<User> users = session.createNamedQuery("findAllUsers", User.class).list();
		System.out.println(users);
		session.close();
	}

	private static void testHQLQuery() {
		System.out.println("HQL Query");
		Session session = sf.openSession();
		List<User> users = session.createQuery("from User", User.class).list();
		System.out.println(users);
		session.close();
	}

	private static void testNativeQuery() {
		System.out.println("Native Query");
		Session session = sf.openSession();
		List<User> users = session.createNativeQuery("select * from users", User.class).list();
		System.out.println(users);
		session.close();
	}

	// select * from users where name = ?
	private static void testCriteriaQuery(String name) {
		System.out.println("criteriaQuery");
		Session session = sf.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);
		Root<User> root = cr.from(User.class);
		cr.select(root).where(cb.equal(root.get("name"), name));
		List<User> users = session.createQuery(cr).list();
		System.out.println(users);
		session.close();
	}

}
