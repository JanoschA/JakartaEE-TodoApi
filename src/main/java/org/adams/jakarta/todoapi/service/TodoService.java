package org.adams.jakarta.todoapi.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.adams.jakarta.todoapi.mapper.TodoEntityMapper;
import org.adams.jakarta.todoapi.mapper.TodoMapper;
import org.adams.jakarta.todoapi.model.Todo;
import org.adams.jakarta.todoapi.repository.TodoRepository;

import java.util.List;
import java.util.UUID;

@Named
public class TodoService {

    private final TodoRepository todoRepository;

    @Inject
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos().stream().map(TodoMapper::toTodo).toList();
    }

    public Todo getTodoById(String id) {
        var todoEntity = todoRepository.getTodoById(UUID.fromString(id));
        if (todoEntity == null) {
            // TODO: remove this and handle the error in the repository
            return null; // or throw an exception
        }
        return TodoMapper.toTodo(todoEntity);
    }

    public Todo createTodo(Todo todo) {
        var todoEntity = todoRepository.createTodo(TodoEntityMapper.toTodoEntity(todo));
        return TodoMapper.toTodo(todoEntity);
    }

    public Todo updateTodo(String id, Todo todo) {
        var todoEntity = TodoEntityMapper.toTodoEntity(todo);
        todoEntity.setId(UUID.fromString(id));
        todoEntity = todoRepository.updateTodo(todoEntity);
        return TodoMapper.toTodo(todoEntity);
    }

    public void deleteTodo(String id) {
        todoRepository.deleteTodo(id);
    }

    public Todo markTodoAsCompleted(String id) {
        var todoEntity = todoRepository.getTodoById(UUID.fromString(id));
        todoEntity.setCompleted(true);

        var todoEntityUpdated = todoRepository.updateTodo(todoEntity);
        return TodoMapper.toTodo(todoEntityUpdated);
    }
}