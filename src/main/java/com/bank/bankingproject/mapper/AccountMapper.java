package com.bank.bankingproject.mapper;

import com.bank.bankingproject.dto.AccountDto;
import com.bank.bankingproject.model.Account;

public class AccountMapper {

    // static mapper for account
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return accountDto;
    }
}
