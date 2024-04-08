package org.adams.jakarta.todoapi.mapper;

import org.adams.jakarta.todoapi.entity.TodoEntity;
import org.adams.jakarta.todoapi.model.Todo;

import java.util.UUID;


public class TodoEntityMapper {

    public static TodoEntity toTodoEntity(Todo todo) {
        TodoEntity todoEntity = new TodoEntity();
        if (todo.getId() != null) {
            todoEntity.setId(UUID.fromString(todo.getId()));
        }
        todoEntity.setTitle(todo.getTitle());
        todoEntity.setDescription(todo.getDescription());
        todoEntity.setDate(todo.getDate());
        todoEntity.setDuration(todo.getDuration());
        todoEntity.setPriority(mapPriority(todo.getPriority()));
        todoEntity.setCompleted(todo.getCompleted());
        return todoEntity;
    }

    private static int mapPriority(Todo.PriorityEnum priority) {
        return switch (priority) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> 3;
        };
    }
}