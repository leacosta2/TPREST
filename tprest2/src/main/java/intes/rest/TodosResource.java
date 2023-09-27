package intes.rest;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("/todos")
public class TodosResource {

	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Todo> getTodos() {
		/* 
		List<String> tags = new LinkedList<String>();
		tags.add("Vie quotidienne");
		List<Todo> todos = new LinkedList<Todo>();
		todos.add(new Todo("1", "Faire les courses", "10/10/23", tags));
		todos.add(new Todo("2", "Reserver train Paris", "9/9/23", tags)); 
		*/
		List<Todo> todos = new LinkedList<Todo>();
		todos.addAll(Todo.valuesStore());
		return todos;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTodo(Todo todo) {
		String id = todo.getId();
		Response resp;
		if (Todo.containStore(id)) {
			resp = Response.noContent().build();
		} else {
			Todo.putStore(id, todo);
			String uriBase = uriInfo.getAbsolutePath().toString();
			URI uriTodo = URI.create(uriBase + id);
			resp = Response.created(uriTodo).build();
		}
		return resp;

	}
	
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTodo(@PathParam("id") String id, Todo todo) {
		Response resp; 
		if (!Todo.containStore(id)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else {
			Todo current = Todo.getStore(id);
			// handle new description
			String newDescription = todo.getDescription();
			if (newDescription != null && newDescription != "" && 
					current.getDescription() != newDescription) {
				current.setDescription(newDescription);
			}
			// handle done
			boolean done = todo.isDone();
			if (current.isDone() != done) {
				current.setDone(done);
			}
			return Response.ok(current).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTodo(@PathParam("id") String id) { 
		if (!Todo.containStore(id)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else {
			Todo.removeStore(id);
			return Response.noContent().build();
		}
	}

}