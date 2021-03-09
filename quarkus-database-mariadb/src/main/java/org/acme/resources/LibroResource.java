package org.acme.resources;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.acme.clases.Libro;




@Path("/libro")
public class LibroResource {
	
	@Inject
	EntityManager entityManager;
	
	//Crea un libro en la base de datos
	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearLibro(Libro libro) {
		entityManager.persist(libro);
		return Response.ok(URI.create("/libro/" + libro.getId())).build();
	}
	
	//Devuelve un libro de la base de datos por id
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Libro getLibro(@PathParam("id")Integer id) {
		return entityManager.find(Libro.class, id);
	}
	
	//devuelve la lista de libros
		@GET
	    @Path("all")
		@Produces(MediaType.APPLICATION_JSON)
	    public List<Libro> getLista() {
	        return entityManager.createNamedQuery("Libro.findAll", Libro.class)
	                .getResultList();
	    }
	
	//Borra un libro de la base de datos por id
	@DELETE
	@Transactional
	@Path("{id}")
	public void borrarLibro(@PathParam("id")Integer id) {
		 entityManager.remove(entityManager.find(Libro.class, id));
	}
	
	
	@PUT
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseBuilder actualizarLibro(Libro libro){
		entityManager.refresh(libro);
		return Response.ok(libro);
	}
	
	

}
