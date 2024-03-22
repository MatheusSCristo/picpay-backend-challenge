package com.matheus.picpay.domain.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction_ent")
@Table(name = "transaction_table")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="value_column")
    private Double value;
    private String payerCPF;
    private String payeeCPF;

    public Transaction(TransactionDTO transactionDto){
        this.payerCPF=transactionDto.getPayerCpf();
        this.payeeCPF=transactionDto.getPayeeCpf();
        this.value=transactionDto.getValue();
    }


}
