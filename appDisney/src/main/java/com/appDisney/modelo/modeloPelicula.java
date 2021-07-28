package com.appDisney.modelo;

import java.util.Date;
import java.util.List;

public class modeloPelicula {
	private long idPelicula;
	private String imagen;
	private String titulo;
	private Date fechaCreacion;
	private int calificacion;
	private List<modeloPersonaje> personajes;
	private modeloGenero genero;

	public modeloPelicula() {
		super();
	}

	public modeloPelicula(long idPelicula, String imagen, String titulo, Date fechaCreacion, int calificacion,
			List<modeloPersonaje> personajes, modeloGenero genero) {
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
}