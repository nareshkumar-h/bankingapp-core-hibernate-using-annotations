package com.naresh.bankingapp.util;

import org.hibernate.SessionFactory;

public class TestHibernateUtil {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println(sessionFactory);
	}
}
