package org.acme.clases;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

@Table(name="Libro")
@Entity
@NamedQuery(name = "Libro.findAll",
query = "SELECT l FROM org.acme.clases.Libro l",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class Libro {
//public class Libro extends PanacheEntity{
@Id
//la notacion de hacer el id autoincremental es la que daba errores en el codigo.
private int id;
@Column(unique=true)
private int isbn;
@Column
private String titulo;

public Libro() {
	
}

public Libro(int id, int isbn, String titulo) {
	super();
	this.id = id;
	this.isbn = isbn;
	this.titulo = titulo;
}

//public Libro(int isbn, String titulo) {
//super();
//this.isbn = isbn;
//this.titulo = titulo;
//}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIsbn() {
	return isbn;
}
public void setIsbn(int isbn) {
	this.isbn = isbn;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Libro other = (Libro) obj;
	if (id != other.id)
		return false;
	return true;
}

}