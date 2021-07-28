package com.appDisney.entidad;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.appDisney.modelo.*;
@Entity
@Table(name = "pelicula")

public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long idPelicula;
	
	@Column(name = "imagen")
	@NotEmpty(message = "La imagen no puede estar vacia")
	private String imagen;
	
	@Column(name = "titulo")
	@NotEmpty(message = "El campo titulo no puede estar vacio")
	private String titulo;
	
	@Column(name = "fechaCreacion")
	@NotEmpty(message = "El campo fecha no puede estar vacio")
	private Date fechaCreacion;
	
	@Column(name = "calificacion")
	@NotEmpty(message = "El campo calificacion no puede estar vacio")
	@Size(min = 1, max = 5)
	private int calificacion;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="idGenero", nullable = false)
	private Genero genero;
	
	@ManyToMany(targetEntity = Personaje.class, mappedBy = "peliculas", cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private List<Personaje> personajes;

	public long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}
	
	
}
