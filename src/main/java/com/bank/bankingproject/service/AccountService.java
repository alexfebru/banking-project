package com.bank.bankingproject.service;

import com.bank.bankingproject.dto.AccountDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(UUID id);
    AccountDto deposit(UUID id, Double amount);
    AccountDto withdraw(UUID id, Double amount);

    List<AccountDto> getAllAccounts();
}
