package com.naresh.bankingapp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.naresh.bankingapp.dao.UserDAO;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public void insert(User user) throws DBException {
		Session sess = sf.openSession();
		try {

			sess.beginTransaction();
			sess.save(user);
			sess.getTransaction().commit();
			System.out.println("UserDAO->insert");
		} catch (HibernateException e) {
			throw new DBException("Unable to insert user", e);
		} finally {
			sess.close();
		}
	}


	public List<User> list() throws DBException {
		Session sess = sf.openSession();
		List<User> userList  = null;
		try {
			Query<User> createQuery = sess.createQuery("from User u", User.class);
			userList = createQuery.getResultList();
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch user", e);
		} finally {
			sess.close();
		}
		return userList;
	}

	public User findOne(int userId) throws DBException {
		Session sess = sf.openSession();
		User user = null;
		try {

			sess.beginTransaction();
			user = sess.get(User.class, userId);
			sess.getTransaction().commit();
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch user", e);
		} finally {
			sess.close();
		}
		return user;
	}

	public void update(User user) throws DBException {
		Session sess = sf.openSession();
		try {

			sess.beginTransaction();
			sess.update(user);
			sess.getTransaction().commit();
			System.out.println("UserDAO->Update");
		} catch (HibernateException e) {
			throw new DBException("Unable to update user", e);
		} finally {
			sess.close();
		}
	}

	public void delete(User user) throws DBException {
		Session sess = sf.openSession();
		try {

			sess.beginTransaction();
			sess.delete(user);
			sess.getTransaction().commit();
			System.out.println("UserDAO->delete");
		} catch (HibernateException e) {
			throw new DBException("Unable to delete user", e);
		} finally {
			sess.close();
		}
	}

}
