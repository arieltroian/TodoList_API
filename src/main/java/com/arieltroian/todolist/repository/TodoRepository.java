package com.arieltroian.todolist.repository;

import com.arieltroian.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId, Sort sort);
}
