package com.matheus.picpay.service;

import com.matheus.picpay.domain.user.User;
import com.matheus.picpay.domain.user.UserDTO;
import com.matheus.picpay.exceptions.cpfAlreadyExistsException;
import com.matheus.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User create(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getCpf());
        if (optionalUser.isPresent()) {
            throw new cpfAlreadyExistsException();
        } else {
            User newUser = new User(userDTO);
            return userRepository.save(newUser);
        }
    }

}
