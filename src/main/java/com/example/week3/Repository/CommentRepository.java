package com.example.week3.Repository;

import com.example.week3.Entity.Blog;
import com.example.week3.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
