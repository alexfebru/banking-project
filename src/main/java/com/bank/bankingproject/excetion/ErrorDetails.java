package com.bank.bankingproject.excetion;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp,
                           String message,
                           String details,
                           String ErrorDetails){

}

