package com.example.week3.Repository;

import com.example.week3.Dto.BlogResponseDto;
import com.example.week3.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByCreatedAtDesc();
}
