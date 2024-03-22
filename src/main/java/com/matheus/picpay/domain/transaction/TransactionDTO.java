package com.matheus.picpay.domain.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    @NotNull
    @PositiveOrZero
    private Double value;
    @NotNull
    private String payerCpf;
    @NotNull
    private String payeeCpf;
}
