package org.adams.jakarta.todoapi.mapper;

import org.adams.jakarta.todoapi.entity.TodoEntity;
import org.adams.jakarta.todoapi.model.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

    public static Todo toTodo(TodoEntity todoEntity) {
        Todo todo = new Todo();
        todo.setId(todoEntity.getId().toString());
        todo.setTitle(todoEntity.getTitle());
        todo.setDescription(todoEntity.getDescription());
        todo.setDate(todoEntity.getDate());
        todo.setDuration(todoEntity.getDuration());
        todo.setPriority(mapPriority(todoEntity.getPriority()));
        todo.setCompleted(todoEntity.isCompleted());
        return todo;
    }

    private static Todo.PriorityEnum mapPriority(int priority) {
        return switch (priority) {
            case 2 -> Todo.PriorityEnum.MEDIUM;
            case 3 -> Todo.PriorityEnum.HIGH;
            default -> Todo.PriorityEnum.LOW;
        };
    }

    public static List<Todo> toTodoList(List<TodoEntity> todoEntities) {
        return todoEntities.stream()
                .map(TodoMapper::toTodo)
                .collect(Collectors.toList());
    }
}