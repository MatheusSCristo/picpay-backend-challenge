package com.matheus.picpay.service;

import com.matheus.picpay.domain.transaction.Transaction;
import com.matheus.picpay.domain.transaction.TransactionDTO;
import com.matheus.picpay.domain.user.User;
import com.matheus.picpay.exceptions.EmailServiceUnavailable;
import com.matheus.picpay.exceptions.NotAuthorizedException;
import com.matheus.picpay.repository.TransactionRepository;
import com.matheus.picpay.repository.UserRepository;
import com.matheus.picpay.response.NotificationResponse;
import com.matheus.picpay.response.TransactionResponse;
import com.matheus.picpay.utils.TransactionFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebClient webClient;

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    public void create(@Valid TransactionDTO transactionDTOa){

        Transaction transaction =  new Transaction(transactionDTOa);
        TransactionResponse transactionResponse=webClient.get().uri("/5794d450-d2e2-4412-8131-73d0293ac1cc").retrieve().bodyToMono(TransactionResponse.class).block();
        if(transactionResponse!=null && transactionResponse.getMessage().equals("Autorizado")){
            TransactionFunction tf= new TransactionFunction();
            Boolean transactionCompleted=tf.performTransaction(transaction,userRepository,transactionRepository);
            if(transactionCompleted){
               NotificationResponse notificationResponse=webClient.get().uri("/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6").retrieve().bodyToMono(NotificationResponse.class).block();
                if(notificationResponse!=null && notificationResponse.getMessage()){
                    SendEmail(transaction.getPayeeCPF());
                }
                else{
                    throw new EmailServiceUnavailable();
                }
            }
        }
        else{
            throw new NotAuthorizedException();
        }
    }

    public void SendEmail(String payeeCpf){
        Optional<User> optinalPayee = userRepository.findById(payeeCpf);
        if(optinalPayee.isPresent()){
            User payee=optinalPayee.get();
            //Função que realizará o serviço de enviar o email para o usuário que está sendo pago.
            //Email(payee.getEmail())


        }

    }


}
