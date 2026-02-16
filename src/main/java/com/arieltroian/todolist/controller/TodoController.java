package com.arieltroian.todolist.controller;

import com.arieltroian.todolist.entity.Todo;
import com.arieltroian.todolist.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public List<Todo>create(@RequestBody @Valid Todo todo) {
        Long userId = getAuthenticatedUserId();
        return todoService.create(userId, todo);
    }

    @GetMapping
    public List<Todo> list() {
        Long userId = getAuthenticatedUserId();
        return todoService.list(userId);
    }

    @PutMapping("/{id}")
    public List<Todo> update(@PathVariable Long id, @RequestBody @Valid Todo todo) {
        Long userId = getAuthenticatedUserId();
        return todoService.update(userId, id, todo);
    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable Long id) {
        Long userId = getAuthenticatedUserId();
        return todoService.delete(userId, id);
    }

    private Long getAuthenticatedUserId() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
