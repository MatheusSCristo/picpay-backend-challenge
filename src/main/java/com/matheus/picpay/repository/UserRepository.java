package com.matheus.picpay.repository;

import com.matheus.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
