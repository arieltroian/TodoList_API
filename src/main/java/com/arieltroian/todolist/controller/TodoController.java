package com.arieltroian.todolist.controller;

import com.arieltroian.todolist.entity.Todo;
import com.arieltroian.todolist.service.TodoService;
import jakarta.validation.Valid;
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
    List<Todo>create(@RequestHeader("X-USER-ID") Long userId, @RequestBody @Valid Todo todo) {
        return todoService.create(userId, todo);
    }

    @GetMapping
    List<Todo> list(@RequestHeader("X-USER-ID") Long userId) {
        return todoService.list(userId);
    }

    @PutMapping("/{id}")
    List<Todo> update(@RequestHeader("X-USER-ID") Long userId,@PathVariable Long id, @RequestBody @Valid Todo todo) {
        return todoService.update(userId, id, todo);
    }

    @DeleteMapping("/{id}")
    List<Todo> delete(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long id) {
        return todoService.delete(userId, id);
    }
}
