package com.matheus.picpay.utils;

import com.matheus.picpay.domain.transaction.Transaction;
import com.matheus.picpay.domain.user.User;
import com.matheus.picpay.domain.user.enums.UserType;
import com.matheus.picpay.exceptions.PayerBalanceException;
import com.matheus.picpay.exceptions.UserTypeTransactionNotAllowedException;
import com.matheus.picpay.exceptions.cpfNotValid;
import com.matheus.picpay.repository.TransactionRepository;
import com.matheus.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
public class TransactionFunction {

    @Transactional
    public boolean performTransaction(Transaction transaction,UserRepository userRepository,TransactionRepository transactionRepository) {
        Optional<User> optionalPayer = userRepository.findById(transaction.getPayerCPF());
        Optional<User> optionalPayee = userRepository.findById(transaction.getPayeeCPF());
        if (optionalPayee.isEmpty()) {
            throw new cpfNotValid();
        } else if (optionalPayer.isEmpty()) {
            throw new cpfNotValid();
        }
        User payer = optionalPayer.get();
        User payee = optionalPayee.get();
        if (payer.getUserType().equals(UserType.shopkeeper)) {
            throw new UserTypeTransactionNotAllowedException(payer.getCpf());
        }
        else if(payer.getBalance()<transaction.getValue()){
            throw new PayerBalanceException(payer.getCpf());
        }
        else {
            payer.setBalance(payer.getBalance() - transaction.getValue());
            payee.setBalance(payee.getBalance() + transaction.getValue());
            userRepository.saveAll(Arrays.asList(payee, payer));
            transactionRepository.save(transaction);
            return true;
        }
    }
}
