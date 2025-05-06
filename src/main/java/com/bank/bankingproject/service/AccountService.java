package com.bank.bankingproject.service;

import com.bank.bankingproject.dto.AccountDTO;
import com.bank.bankingproject.dto.TransactionDTO;
import com.bank.bankingproject.dto.TransferFundDTO;
import com.bank.bankingproject.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDto);
    AccountDTO getAccountById(UUID id);
    AccountDTO deposit(UUID id, Double amount);
    AccountDTO withdraw(UUID id, Double amount);

    AccountDTO deleteAccount(UUID id);

    List<AccountDTO> getAllAccounts();

    void transferFunds(TransferFundDTO transferFundDTO);

    List<TransactionDTO> getAccountTransactions(UUID id);

}
