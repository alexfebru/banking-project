package com.bank.bankingproject.mapper;

import com.bank.bankingproject.dto.AccountDTO;
import com.bank.bankingproject.model.Account;

public class AccountMapper {

    // static mapper for account
    public static Account mapToAccount(AccountDTO accountDto) {
        Account account = new Account(
                accountDto.id(),
                accountDto.accountHolderName(),
                accountDto.balance()
        );

        return account;
    }

    public static AccountDTO mapToAccountDto(Account account) {
        AccountDTO accountDto = new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return accountDto;
    }
}
