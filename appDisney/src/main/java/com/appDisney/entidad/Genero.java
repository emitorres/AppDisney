package com.appDisney.entidad;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import com.appDisney.modelo.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "genero")

public class Genero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long idGenero;

	@Column(name = "nombre")
	@NotEmpty(message = "El campo nombre no puede estar vacio")
	private String nombre;

	@Column(name = "imagen")
	@NotEmpty(message = "La imagen no puede estar vacia")
	private String imagen;

	@OneToMany(targetEntity = Pelicula.class, mappedBy = "genero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	
	
	//@JsonBackReference
	// @JsonIgnore
	private List<Pelicula> peliculas;

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

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

}
