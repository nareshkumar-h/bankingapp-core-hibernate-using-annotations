package com.naresh.bankingapp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.naresh.bankingapp.dao.AccountDAO;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;
import com.naresh.bankingapp.util.HibernateUtil;

public class AccountDAOImpl implements AccountDAO {

	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public void insert(Account account) throws DBException {
		Session sess = sf.openSession();
		try {

			sess.beginTransaction();
			sess.save(account);
			sess.getTransaction().commit();
			System.out.println("AccountDAO->insert");
		} catch (HibernateException e) {
			throw new DBException("AccountDAO to insert account", e);
		} finally {
			sess.close();
		}
	}

	public List<Account> list() throws DBException {
		Session sess = sf.openSession();
		List<Account> accountList = null;
		try {
			Query<Account> createQuery = sess.createQuery("from Account u", Account.class);
			accountList = createQuery.getResultList();
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch accounts", e);
		} finally {
			sess.close();
		}
		return accountList;
	}
	
	public Account findOne(int accountId) throws DBException {
		Session sess = sf.openSession();
		Account account = null;
		try {
			account = sess.get(Account.class, accountId);
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch account", e);
		} finally {
			sess.close();
		}
		return account;
	}
	
	public List<Account> findMyAccounts(int userId) throws DBException {
		Session sess = sf.openSession();
		List<Account> accountList = null;
		try {
			Query<Account> createQuery = sess.createQuery("from Account u where u.user.id = ?1", Account.class);
			createQuery.setParameter(1, userId);
			accountList = createQuery.getResultList();
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch accounts for the given userId", e);
		} finally {
			sess.close();
		}
		return accountList;
	}
	
	public void delete(Account account) throws DBException {
		Session sess = sf.openSession();
		try {

			sess.beginTransaction();
			sess.delete(account);
			sess.getTransaction().commit();
			System.out.println("AccountDAO->delete");
		} catch (HibernateException e) {
			throw new DBException("Unable to delete account", e);
		} finally {
			sess.close();
		}
	}
}
