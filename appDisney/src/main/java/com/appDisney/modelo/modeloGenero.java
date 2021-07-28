package com.appDisney.modelo;

import java.util.List;

public class modeloGenero {
	private long idGenero;
	private String nombre;
	private String imagen;
	private List<modeloPelicula> peliculas;

	public modeloGenero() {
		super();
	}

	public modeloGenero(long idGenero, String nombre, String imagen, List<modeloPelicula> peliculas) {
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

	public List<modeloPelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<modeloPelicula> peliculas) {
		this.peliculas = peliculas;
	}

}
