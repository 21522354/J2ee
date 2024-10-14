package com.namdam1123.replicationdemo.demo.Repositories;

import com.namdam1123.replicationdemo.demo.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
}
