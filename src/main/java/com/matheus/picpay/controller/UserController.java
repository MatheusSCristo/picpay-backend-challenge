package com.matheus.picpay.controller;

import com.matheus.picpay.domain.user.User;
import com.matheus.picpay.domain.user.UserDTO;
import com.matheus.picpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO userDto){
        System.out.println(userDto.getCpf());
        userService.create(userDto);
        return ResponseEntity.ok().build();
    }


}


