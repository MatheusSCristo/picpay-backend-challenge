package com.matheus.picpay.domain.user;

import com.matheus.picpay.domain.user.enums.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    @NotNull(message="O campo CPF deve ser preenchido.")
    private String cpf;
    @NotNull(message="O campo nome deve ser preenchido.")
    private String name;
    @NotNull(message="O campo email deve ser preenchido.")
    private String email;
    @NotNull(message="O campo senha deve ser preenchido.")
    private String password;
    @NotNull(message="O campo userType deve ser preenchido.")
    private UserType userType;
    @NotNull(message="O campo balance deve ser preenchido.")
    @PositiveOrZero(message = "O campo balance deve ser um número maior ou igual a zero.")
    private Double balance;
}
