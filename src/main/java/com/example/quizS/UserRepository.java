package com.example.quizS;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.quizS.User;

public interface UserRepository extends JpaRepository<User, Long> {
    }