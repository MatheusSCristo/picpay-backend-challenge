package com.matheus.picpay.controller;

import com.matheus.picpay.domain.transaction.Transaction;
import com.matheus.picpay.domain.transaction.TransactionDTO;
import com.matheus.picpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(){
        List<Transaction> transactionList = transactionService.getAll();
        return ResponseEntity.ok().body(transactionList);
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid TransactionDTO transactionDTO){
        transactionService.create(transactionDTO);
        return ResponseEntity.ok().build();
    }

}
