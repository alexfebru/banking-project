package com.bank.bankingproject.dto;

import java.util.UUID;

public record TransferFundDTO(UUID fromAccountID, UUID toAccountID, double amount) {
}
