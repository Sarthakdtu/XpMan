package com.xpman.service.impl;

import com.xpman.dao.AccountDao;
import com.xpman.model.Account;
import com.xpman.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public List<Account> read() throws GeneralSecurityException, IOException {
       return accountDao.getAll();
    }

    @Override
    public void write(Account account) throws GeneralSecurityException, IOException {
        accountDao.create(account);
    }

    @Override
    public Account getAccountById(Integer id) throws GeneralSecurityException, IOException {
        return accountDao.getAccountById(id);
    }

    @Override
    public void updateAccountById(Account account) throws GeneralSecurityException, IOException {
        accountDao.updateAccountById(account);
    }

    @Override
    public void deleteAccountById(Integer id) throws GeneralSecurityException, IOException {
        accountDao.deleteAccountById(id);
    }
}
