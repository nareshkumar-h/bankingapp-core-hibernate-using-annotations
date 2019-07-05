package com.naresh.bankingapp.dao;

import java.util.List;

import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;

public interface AccountDAO {
	void insert(Account account) throws DBException;
	List<Account> list() throws DBException;
	Account findOne(int accountId) throws DBException;
	void delete(Account account) throws DBException ;
}
