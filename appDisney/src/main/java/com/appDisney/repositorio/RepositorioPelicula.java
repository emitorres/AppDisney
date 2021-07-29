package com.appDisney.repositorio;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appDisney.entidad.*;
@Repository
public interface RepositorioPelicula extends JpaRepository<Pelicula, Long>{

	

	ArrayList<Pelicula> findByTitulo(String nombre);

	ArrayList<Pelicula> findByGenero(int idGenero);
	//@Query("SELECT * FROM pelicula order by ?1%")
	//ArrayList<Pelicula> findByOrderByTitulo(String orden);

}
