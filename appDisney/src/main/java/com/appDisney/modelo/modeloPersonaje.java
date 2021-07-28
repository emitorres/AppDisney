package com.appDisney.modelo;

import java.util.List;

public class modeloPersonaje {
	private long idPersonaje;
	private String imagen;
	private String nombre;
	private int edad;
	private double peso;
	private String historia;
	private List<modeloPelicula> peliculas;
	
	
	public modeloPersonaje() {
		super();
	}
	
	
	public modeloPersonaje(long idPersonaje, String imagen, String nombre, int edad, double peso, String historia,
			List<modeloPelicula> peliculas) {
		super();
		this.idPersonaje = idPersonaje;
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
	}


	public long getIdPersonaje() {
		return idPersonaje;
	}


	public void setIdPersonaje(long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}


	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public List<modeloPelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<modeloPelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	
}
