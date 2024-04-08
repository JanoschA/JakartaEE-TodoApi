package org.adams.jakarta.todoapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.adams.jakarta.todoapi.entity.TodoEntity;
import org.adams.jakarta.todoapi.mapper.TodoEntityMapper;

import java.util.List;
import java.util.UUID;

public class TodoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TodoEntity> getAllTodos() {
        return entityManager.createQuery("SELECT t FROM TodoEntity t", TodoEntity.class).getResultList();
    }

    public TodoEntity getTodoById(UUID id) {
        var todoEntity = entityManager.find(TodoEntity.class, id);
        // TODO: handle null and throw a error
        return todoEntity;
    }

    @Transactional
    public TodoEntity createTodo(TodoEntity todo) {
        entityManager.persist(todo);
        return todo;
    }

    @Transactional
    public TodoEntity updateTodo(TodoEntity todoEntity) {
        var todoEntityLoaded = getTodoById(todoEntity.getId());
        if (todoEntityLoaded == null) {
            return null;
        }
        return entityManager.merge(todoEntity);
    }

    @Transactional
    public void deleteTodo(String id) {
        TodoEntity todo = getTodoById(UUID.fromString(id));
        if (todo != null) {
            entityManager.remove(todo);
        }
    }
}