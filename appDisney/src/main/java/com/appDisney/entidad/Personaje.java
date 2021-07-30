package com.appDisney.entidad;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "personaje")

public class Personaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long idPersonaje;

	@Column(name = "imagen")
	@NotEmpty(message = "La imagen no puede estar vacia")
	private String imagen;

	@Column(name = "nombre")
	@NotEmpty(message = "El campo nombre no puede estar vacio")
	private String nombre;

	@Column(name = "edad")
	@NotNull(message = "El campo edad no puede estar vacio")
	private int edad;

	@Column(name = "peso")
	@NotNull(message = "El campo peso no puede estar vacio")
	private double peso;

	@Column(name = "historia")
	@NotEmpty(message = "El campo historia no puede estar vacio")
	private String historia;
	
	@ManyToMany(targetEntity = Pelicula.class, cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH })
	
	
	//@JsonBackReference
	private List<Pelicula> peliculas;

	
	public Personaje() {
		//super();
	}

	

	public Personaje(@NotEmpty(message = "La imagen no puede estar vacia") String imagen,
			@NotEmpty(message = "El campo nombre no puede estar vacio") String nombre) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
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
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	
}
