package com.appDisney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appDisney.entidad.*;
@Repository
public interface RepositorioPelicula extends JpaRepository<Pelicula, Long>{

}
