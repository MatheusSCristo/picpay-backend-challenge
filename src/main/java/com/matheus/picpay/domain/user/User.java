package com.matheus.picpay.domain.user;

import com.matheus.picpay.domain.user.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

@Entity(name ="user")
@Table(name="tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Column(unique=true) @Id
    private String cpf ;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private Double balance;

    public User(UserDTO userDTO){
        this.name=userDTO.getName();
        this.cpf=userDTO.getCpf();
        this.userType=userDTO.getUserType();
        this.email= userDTO.getEmail();
        this.password=userDTO.getPassword();
        this.balance=userDTO.getBalance();
    }

}
