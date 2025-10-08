package com.example.payments.controller;

import org.springframework.http.ResponseEntity;

public class PaymentsController {

    public ResponseEntity<Boolean> pay()
    {
        return ResponseEntity.ok(true);
    }
}
