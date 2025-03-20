package com.bank.bankingproject.service;

import com.bank.bankingproject.dto.AccountDto;

import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(UUID id);
}
