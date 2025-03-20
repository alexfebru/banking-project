package com.bank.bankingproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountDto {
    private UUID id;
    private String accountHolderName;
    private double balance;
}
