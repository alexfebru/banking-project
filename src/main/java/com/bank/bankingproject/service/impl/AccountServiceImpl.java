package com.bank.bankingproject.service.impl;

import com.bank.bankingproject.dto.AccountDto;
import com.bank.bankingproject.mapper.AccountMapper;
import com.bank.bankingproject.model.Account;
import com.bank.bankingproject.repository.AccountRepository;
import com.bank.bankingproject.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(UUID id) {
        return null;
    }
}
