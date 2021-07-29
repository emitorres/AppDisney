package com.appDisney.repositorio;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.appDisney.entidad.Personaje;

@Repository
public interface RepositorioPersonaje extends JpaRepository<Personaje, Long>{

	ArrayList<Personaje> findByNombre(String nombre);

	ArrayList<Personaje> findByEdad(int edad);
	

	
}
