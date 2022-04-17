package com.xpman.service;

import com.xpman.model.Account;
import com.xpman.service.impl.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface AccountService {

    List<Account> read() throws GeneralSecurityException, IOException;

    void write(Account account) throws GeneralSecurityException, IOException ;

    Account getAccountById(Integer id) throws GeneralSecurityException, IOException ;

    void updateAccountById(Account account) throws GeneralSecurityException, IOException ;

    void deleteAccountById(Integer id) throws GeneralSecurityException, IOException ;
}
