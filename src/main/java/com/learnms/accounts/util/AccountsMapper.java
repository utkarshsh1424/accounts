package com.learnms.accounts.util;

import com.learnms.accounts.dto.AccountsDto;
import com.learnms.accounts.entity.Accounts;

/**
 * Mapper class for accounts dto to entity and vice-versa.
 */
public class AccountsMapper {

    /**
     * This method will map the account entity to accountsDto response.
     * @param accounts {@link Accounts }
     * @return {@link AccountsDto}
     */
    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return AccountsDto.builder().accountType(accounts.getBranch())
                .accountNumber(accounts.getAccountNumber())
                .branch(accounts.getBranch())
                .build();
    }

    /**
     * Mapper method to map the accountsDto class to Account entity.
     * @param accountsDto {@link AccountsDto}
     * @return retrun {@link Accounts} object.
     */
    public static Accounts mapToAccounts(AccountsDto accountsDto, Long customerId) {
        return Accounts.builder().accountType(accountsDto.getAccountType())
                .accountNumber(accountsDto.getAccountNumber())
                .customerId(customerId)
                .branch(accountsDto.getBranch())
                .build();
    }
}
