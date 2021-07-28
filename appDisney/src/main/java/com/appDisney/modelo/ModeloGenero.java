package com.appDisney.modelo;

import java.util.List;

public class ModeloGenero {
	private long idGenero;
	private String nombre;
	private String imagen;
	private List<ModeloPelicula> peliculas;

	public ModeloGenero() {
		super();
	}

	public ModeloGenero(long idGenero, String nombre, String imagen, List<ModeloPelicula> peliculas) {
		super();
		this.idGenero = idGenero;
		this.nombre = nombre;
		this.imagen = imagen;
		this.peliculas = peliculas;
	}

	public long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<ModeloPelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<ModeloPelicula> peliculas) {
		this.peliculas = peliculas;
	}

}
