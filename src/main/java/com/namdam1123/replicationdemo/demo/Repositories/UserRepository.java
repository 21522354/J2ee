package com.namdam1123.replicationdemo.demo.Repositories;

import com.namdam1123.replicationdemo.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
