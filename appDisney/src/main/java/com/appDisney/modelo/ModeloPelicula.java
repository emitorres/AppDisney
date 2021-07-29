package com.appDisney.modelo;

import java.util.Date;
import java.util.List;

public class ModeloPelicula {
	private long idPelicula;
	private String imagen;
	private String titulo;
	private Date fechaCreacion;
	private int calificacion;
	private List<ModeloPersonaje> personajes;
	private ModeloGenero genero;

	public ModeloPelicula() {
		super();
	}

	public ModeloPelicula(long idPelicula, String imagen, String titulo, Date fechaCreacion, int calificacion,
			List<ModeloPersonaje> personajes, ModeloGenero genero) {
		super();
		this.idPelicula = idPelicula;
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
		this.personajes = personajes;
		this.genero = genero;
	}

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

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public List<ModeloPersonaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<ModeloPersonaje> personajes) {
		this.personajes = personajes;
	}

	public ModeloGenero getGenero() {
		return genero;
	}

	public void setGenero(ModeloGenero genero) {
		this.genero = genero;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}