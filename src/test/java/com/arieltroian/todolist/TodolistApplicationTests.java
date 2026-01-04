package com.arieltroian.todolist;

import com.arieltroian.todolist.entity.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TodolistApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testCreateTodoSuccess() throws Exception {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		mockMvc.perform(
						post("/todos")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(todo))
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].name").value(todo.getName()))
				.andExpect(jsonPath("$[0].description").value(todo.getDescription()))
				.andExpect(jsonPath("$[0].finished").value(todo.isFinished()))
				.andExpect(jsonPath("$[0].priority").value(todo.getPriority()));
	}

	@Test
	void testCreateTodoFailure() throws Exception {
		var invalidTodo = new Todo("", "", false, 0);

		mockMvc.perform(
						post("/todos")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(invalidTodo))
				)
				.andExpect(status().isBadRequest());
	}
}
