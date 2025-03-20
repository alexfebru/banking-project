package com.bank.bankingproject.service.impl;

import com.bank.bankingproject.dto.AccountDto;
import com.bank.bankingproject.excetion.AccountException;
import com.bank.bankingproject.mapper.AccountMapper;
import com.bank.bankingproject.model.Account;
import com.bank.bankingproject.repository.AccountRepository;
import com.bank.bankingproject.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(UUID id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );
        double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(UUID id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );

        if (account.getBalance() < amount) {
            throw new AccountException("Insufficient balance");
        }

        double totalAmount = account.getBalance() - amount;
        account.setBalance(totalAmount);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto deleteAccount(UUID id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );
        accountRepository.delete(account);
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }
}
