package com.bank.bankingproject.service.impl;

import com.bank.bankingproject.dto.AccountDTO;
import com.bank.bankingproject.dto.TransactionDTO;
import com.bank.bankingproject.dto.TransferFundDTO;
import com.bank.bankingproject.excetion.AccountException;
import com.bank.bankingproject.mapper.AccountMapper;
import com.bank.bankingproject.model.Account;
import com.bank.bankingproject.model.Transaction;
import com.bank.bankingproject.repository.AccountRepository;
import com.bank.bankingproject.repository.TransactionRepository;
import com.bank.bankingproject.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT !";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW !";
    private static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER !";
    public AccountServiceImpl(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;

    }

    // Create account with accountDto
    @Override
    public AccountDTO createAccount(AccountDTO accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    // Get account or findAccount byId
    @Override
    public AccountDTO getAccountById(UUID id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );
        return AccountMapper.mapToAccountDto(account);
    }

    // Method deposit from accountId
    @Override
    public AccountDTO deposit(UUID id, Double amount) {

        // Deposit with accountId
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );
        double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account saveAccount = accountRepository.save(account);

        // Transaction account by id with deposit method
        Transaction transaction = new Transaction();

    //  Transaction with deposit type
        // Here we can use id direct
        //transaction.setAccountID(account.getId());
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);


        return AccountMapper.mapToAccountDto(saveAccount);
    }

    // Withdraw with accountId
    @Override
    public AccountDTO withdraw(UUID id, Double amount) {
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

        // Transaction account by id with deposit method
        Transaction transaction = new Transaction();

        //  Transaction with deposit type
        // Here we can use id direct
        //transaction.setAccountID(account.getId());
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(saveAccount);
    }

    // Delete account by using accountId
    @Override
    public AccountDTO deleteAccount(UUID id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()-> new AccountException("Account ID: " + id + "not found")
                );
        accountRepository.delete(account);
        return null;
    }

    // Get all account from database
    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    // Transfer funds account and account by using id fromAccount to toAccount
    @Override
    public void transferFunds(TransferFundDTO transferFundDTO) {

        // Retrieve the account from which send the amount

        Account fromAccount = accountRepository
                .findById(transferFundDTO.fromAccountID())
                .orElseThrow(()-> new AccountException("Account ID: " + transferFundDTO.fromAccountID() + "not found"));

        // Retrieve the account to which we send the amount

        Account toAccount = accountRepository
                .findById(transferFundDTO.toAccountID())
                .orElseThrow(()-> new AccountException("Account ID: " + transferFundDTO.toAccountID() + "not found"));


        if(fromAccount.getBalance() < transferFundDTO.amount()) {
            throw new AccountException("Insufficient balance");
        }
        // Debit the amount from fromAccount object
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDTO.amount());

        // Credit the amount to toAccount object
        toAccount.setBalance(toAccount.getBalance() + transferFundDTO.amount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();

        transaction.setAccountId(transferFundDTO.fromAccountID());
        transaction.setAmount(transferFundDTO.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);


    }

    @Override
    public List<TransactionDTO> getAccountTransactions(UUID accountId) {

        // This method retrieve transaction from accountId
        List<Transaction> transactions = transactionRepository
                .findByAccountIdOrderByTransactionDateDesc(accountId);

        return transactions.stream()
                .map((transaction -> ConvertEntityToDto(transaction)))
                .collect(Collectors.toList());
    }

    private TransactionDTO ConvertEntityToDto(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTransactionDate()
        );
    }

}
