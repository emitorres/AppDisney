package com.appDisney.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.appDisney.entidad.Personaje;

@Repository
public interface RepositorioPersonaje extends JpaRepository<Personaje, Long>{

}
