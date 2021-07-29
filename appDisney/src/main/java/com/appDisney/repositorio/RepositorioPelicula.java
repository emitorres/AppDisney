package com.appDisney.repositorio;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appDisney.entidad.*;
@Repository

public interface RepositorioPelicula extends JpaRepository<Pelicula, Long>{

	

	ArrayList<Pelicula> findByTitulo(String nombre);

	
	
	
	
	ArrayList<Pelicula> findAll();





	










	//ArrayList<Pelicula> findByIdGenero(int idGenero);



}
