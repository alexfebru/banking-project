package com.bank.bankingproject.mapper;

import com.bank.bankingproject.dto.AccountDto;
import com.bank.bankingproject.model.Account;

public class AccountMapper {

    // static mapper for account
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.id(),
                accountDto.accountHolderName(),
                accountDto.balance()
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
