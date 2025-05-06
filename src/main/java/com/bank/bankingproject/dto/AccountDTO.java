package com.bank.bankingproject.dto;

//This is a Dto class that is old style write
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//import java.util.UUID;
//
//@Data
//@AllArgsConstructor
//public class AccountDto {
//    private UUID id;
//    private String accountHolderName;
//    private double balance;
//}

// New Style using Record Dto

import java.util.UUID;

public record AccountDTO(UUID id, String accountHolderName, double balance) {

}
