package com.xpman.dao;

import com.xpman.model.Account;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface AccountDao {
    List<Account> getAll() throws GeneralSecurityException, IOException;

    void create(Account account) throws GeneralSecurityException, IOException;

    Account getAccountById(Integer id) throws GeneralSecurityException, IOException;

    void updateAccountById(Account account) throws GeneralSecurityException, IOException;

    void deleteAccountById(Integer id) throws GeneralSecurityException, IOException;
}
