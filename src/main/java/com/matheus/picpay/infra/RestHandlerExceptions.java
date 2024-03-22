package com.matheus.picpay.infra;

import com.matheus.picpay.exceptions.*;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestHandlerExceptions extends ResponseEntityExceptionHandler {


    @ExceptionHandler(cpfAlreadyExistsException.class)
    private ResponseEntity<ResponseExceptions> cpfExistsErrorHandler(cpfAlreadyExistsException exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("O CPF informado já está cadastrado no sistema.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }

    @ExceptionHandler(cpfNotValid.class)
    private ResponseEntity<ResponseExceptions> cpfDoesntExistsHandlerError(cpfNotValid exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("O CPF informado não está cadastrado no sistema.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }

    @ExceptionHandler(EmailServiceUnavailable.class)
    private ResponseEntity<ResponseExceptions> emailErrorHandler(EmailServiceUnavailable exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("O serviço de email está indisponível, notificação não enviada.", HttpStatus.SERVICE_UNAVAILABLE);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }
    @ExceptionHandler(UserTypeTransactionNotAllowedException.class)
    private ResponseEntity<ResponseExceptions> userNotAuthorizedErrorHandler(UserTypeTransactionNotAllowedException exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("Um usuário do tipo lojista não pode realizar transações.",HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }

    @ExceptionHandler(PayerBalanceException.class)
    private ResponseEntity<ResponseExceptions> payerBalanceErrorHandler(PayerBalanceException exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("O usuário que solicitou a transação não possui saldo suficiente.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }
    @ExceptionHandler(NotAuthorizedException.class)
    private ResponseEntity<ResponseExceptions> operationNotAuthorizedErroHandler(NotAuthorizedException exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("A operação não foi autorizada, tente novamente mais tarde.", HttpStatus.SERVICE_UNAVAILABLE);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ResponseExceptions> internalErrorHandler(RuntimeException exception){
        ResponseExceptions responseExceptions = new ResponseExceptions("O CPF informado já está cadastrado no sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }


}
