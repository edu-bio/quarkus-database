package org.acme.resources;

import java.net.URI;
import java.util.List;

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

import org.acme.clases.Libro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;




@Path("/libro")
public class LibroResource {
	//Asi sería la clase usando EntityManager
//	@Inject
//	EntityManager entityManager;
//	
//	//Crea un libro en la base de datos
//	@POST
//	@Transactional
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response crearLibro(Libro libro) {
//		entityManager.persist(libro);
//		return Response.ok(URI.create("/libro/" + libro.getId())).build();
//	}
//	
//	//Devuelve un libro de la base de datos por id
//	@GET
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Libro getLibro(@PathParam("id")Integer id) {
//		return entityManager.find(Libro.class, id);
//	}
//	
//	//devuelve la lista de libros
//		@GET
//	    @Path("all")
//		@Produces(MediaType.APPLICATION_JSON)
//	    public List<Libro> getLista() {
//	        return entityManager.createNamedQuery("Libro.findAll", Libro.class)
//	                .getResultList();
//	    }
//	
//	//Borra un libro de la base de datos por id
//	@DELETE
//	@Transactional
//	@Path("{id}")
//	public void borrarLibro(@PathParam("id")Integer id) {
//		 entityManager.remove(entityManager.find(Libro.class, id));
//	}
//	
//	
//	@PUT
//	@Transactional
//	@Consumes(MediaType.APPLICATION_JSON)
//	public ResponseBuilder actualizarLibro(Libro libro){
//		entityManager.refresh(libro);
//		return Response.ok(libro);
//	}
	
	//Asi seria usando Panache
	
	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearLibro(Libro libro) {
		libro.persist();
		return Response.ok(URI.create("/libro/" + libro.id)).build();
	}
	
	//Devuelve un libro de la base de datos por id
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Libro getLibro(@PathParam("id")Long id) {
		return Libro.findById(id);
	}
	
	//devuelve la lista de libros
		@GET
	    @Path("all")
		@Produces(MediaType.APPLICATION_JSON)
	    public List<Libro> getLista() {
	        return Libro.findAll().list();
	    }
	
	//Borra un libro de la base de datos por id
	@DELETE
	@Transactional
	@Path("{id}")
	public void borrarLibro(@PathParam("id")Long id) {
		 Libro.deleteById(id);
	}
	
	//actualiza un libro en la base de datos
	@PUT
	@Path("/{id}")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Libro actualizarLibro(@PathParam("id") Long id, Libro newLibro){
		Libro libro = Libro.findById(id);
		libro.setTitulo(newLibro.getTitulo());
		libro.setIsbn(newLibro.getIsbn());
		return libro;

	
	}
	
	

}
