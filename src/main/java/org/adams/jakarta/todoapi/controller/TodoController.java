package org.adams.jakarta.todoapi.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.adams.jakarta.todoapi.model.Todo;
import org.adams.jakarta.todoapi.service.TodoService;

import java.util.List;

@Path("todos")
public class TodoController {

    private final TodoService todoService;

    @Inject
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Todo getTodo(@PathParam("id") String id) {
        return todoService.getTodoById(id);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Todo createTodo(Todo todo) {
        return todoService.createTodo(todo);
    }

    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Todo updateTodo(@PathParam("id") String id, Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") String id) {
        todoService.deleteTodo(id);
    }

    @PUT
    @Path("{id}/complete")
    @Produces({ MediaType.APPLICATION_JSON })
    public Todo markTodoAsCompleted(@PathParam("id") String id) {
        return todoService.markTodoAsCompleted(id);
    }
}