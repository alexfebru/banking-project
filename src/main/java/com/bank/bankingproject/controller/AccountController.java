package com.bank.bankingproject.controller;

import com.bank.bankingproject.dto.AccountDTO;
import com.bank.bankingproject.dto.TransactionDTO;
import com.bank.bankingproject.dto.TransferFundDTO;
import com.bank.bankingproject.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;



@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Get Account By ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable UUID id) {
        AccountDTO accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Create Account REST API
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Deposit Account REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> depositAccount(@PathVariable UUID id, @RequestBody Map<String, Double> request )  {

        Double amount = request.get("amount");
        AccountDTO accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit Account REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdrawAccount(@PathVariable UUID id, @RequestBody Map<String, Double> request)  {
        Double amount = request.get("amount");
        AccountDTO accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Account REST API
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account by ID REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable UUID id) {
        AccountDTO accountDto = accountService.deleteAccount(id);
        return ResponseEntity.ok(accountDto);
    }

    // Transfer Account by REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@Validated @RequestBody TransferFundDTO transferFundDTO) {
        accountService.transferFunds(transferFundDTO);
        return ResponseEntity.ok("Transfer Successfully");
    }

    // Transaction REST API
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> fetchTransactions(@PathVariable("id") UUID accountId) {
        List<TransactionDTO> transactions = accountService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
