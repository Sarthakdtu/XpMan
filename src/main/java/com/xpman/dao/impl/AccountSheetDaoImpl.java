package com.xpman.dao.impl;

import com.xpman.constants.EntitySpreadSheet;
import com.xpman.dao.AccountDao;
import com.xpman.model.Account;
import com.xpman.spreadsheet.GoogleSheetOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountSheetDaoImpl implements AccountDao {

    private final Logger logger = LoggerFactory.getLogger(AccountSheetDaoImpl.class);

    @Override
    public List<Account> getAll() throws GeneralSecurityException, IOException {
        List<List<Object>> rows = GoogleSheetOperation.getAll(EntitySpreadSheet.ACCOUNT);
        List<Account> accounts = new ArrayList<>();
        int count = 2;
        for(List row : rows){
            Account account = Account.builder()
                    .id(count)
                    .name((String) row.get(0))
                    .color((String) row.get(1))
                    .amount(Double.valueOf((String) row.get(2)))
                    .expenditure(Double.valueOf((String) row.get(3)))
                    .build();
            accounts.add(account);
            count += 1;
        }
        return accounts;
    }

    @Override
    public void create(Account account) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.append(EntitySpreadSheet.ACCOUNT, account);
    }

    @Override
    public Account getAccountById(Integer id) throws GeneralSecurityException, IOException {
        List<Object> row = GoogleSheetOperation.getByRowNumber(EntitySpreadSheet.ACCOUNT, id);
        List<Account> accounts = new ArrayList<>();
        return Account.builder()
                .id(id)
                .name((String) row.get(0))
                .color((String) row.get(1))
                .amount(Double.valueOf((String) row.get(2)))
                .expenditure(Double.valueOf((String) row.get(3)))
                .build();
    }

    @Override
    public void updateAccountById(Account account) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.updateByRowNumber(EntitySpreadSheet.ACCOUNT, account, account.getId());
    }

    @Override
    public void deleteAccountById(Integer id) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.deleteByRowNumber(EntitySpreadSheet.ACCOUNT, id);
    }
}
