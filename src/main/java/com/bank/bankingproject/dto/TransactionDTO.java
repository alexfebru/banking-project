package com.bank.bankingproject.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDTO(UUID id,
                             UUID accountId,
                             double amount,
                             String transactionType,
                             LocalDateTime transactionDate) {
}
