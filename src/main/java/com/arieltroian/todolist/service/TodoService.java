package com.arieltroian.todolist.service;

import com.arieltroian.todolist.entity.Todo;
import com.arieltroian.todolist.entity.User;
import com.arieltroian.todolist.repository.TodoRepository;
import com.arieltroian.todolist.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> create(Long userId, @Valid Todo todo) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        todo.setUser(user);

        todoRepository.save(todo);
        return list(userId);
    }

    public List<Todo> list(Long userId) {
        Sort sort = Sort.by("priority").descending()
                .and(Sort.by("name").ascending());

        return todoRepository.findByUserId(userId, sort);
    }

    public List<Todo> update(Long userId, Long id, @Valid Todo todo) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        todo.setId(id);
        todo.setUser(user);

        todoRepository.save(todo);
        return list(userId);
    }

    public List<Todo> delete(Long userId, Long id) {
        todoRepository.deleteById(id);
        return list(userId);
    }
}
