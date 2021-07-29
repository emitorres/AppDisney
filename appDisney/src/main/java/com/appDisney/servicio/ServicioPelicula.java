package com.appDisney.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appDisney.entidad.Genero;
import com.appDisney.entidad.Pelicula;
import com.appDisney.repositorio.RepositorioGenero;
import com.appDisney.repositorio.RepositorioPelicula;
import com.appDisney.repositorio.RepositorioPersonaje;

@Service
public class ServicioPelicula {
	
	private RepositorioPelicula repoPelicula;
	private RepositorioGenero repoGenero;

	
	public ServicioPelicula(RepositorioPelicula repoPelicula, RepositorioGenero repoGenero) {
		super();
		this.repoPelicula = repoPelicula;
		this.repoGenero = repoGenero;
	}

	//@Transactional
	public ResponseEntity<Object> agregarPelicula(Pelicula pelicula) {
		
		Pelicula peli = new Pelicula();
		
		peli.setCalificacion(pelicula.getCalificacion());
		peli.setFechaCreacion(pelicula.getFechaCreacion());
		peli.setImagen(pelicula.getImagen());
		peli.setTitulo(pelicula.getTitulo());
		
		//Genero genero = repoGenero.findById(pelicula.getGenero().getIdGenero()).get();
		
		peli.setGenero(pelicula.getGenero());
		
		
		

		Pelicula nuevaPelicula = repoPelicula.save(peli);

		if (repoPelicula.findById(nuevaPelicula.getIdPelicula()).isPresent())
			return ResponseEntity.ok("Pelicula creada!");
		else
			return ResponseEntity.unprocessableEntity().body("Error al crear la pelicula");

	}

	public ArrayList<Pelicula> obtenerPeliculas() {

		return (ArrayList<Pelicula>) repoPelicula.findAll();
	}

	public ResponseEntity<Pelicula> obtenerPelicula(long idPelicula) {
		Optional<Pelicula> pelicula = repoPelicula.findById(idPelicula);

		if (pelicula.isPresent()) {
			return new ResponseEntity<>(pelicula.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	public ResponseEntity<Object> actualizarPelicula(Pelicula pelicula, Long idPelicula) {
		if (repoPelicula.findById(idPelicula).isPresent()) {
			Pelicula nuevaPelicula = repoPelicula.findById(idPelicula).get();
			nuevaPelicula.setCalificacion(pelicula.getCalificacion());
			nuevaPelicula.setFechaCreacion(pelicula.getFechaCreacion());
			nuevaPelicula.setImagen(pelicula.getImagen());
			nuevaPelicula.setTitulo(pelicula.getTitulo());
			Genero genero = repoGenero.findById(pelicula.getGenero().getIdGenero()).get();
			
			nuevaPelicula.setGenero(genero);
			Pelicula peliculaActualizada = repoPelicula.save(nuevaPelicula);
			if (repoPelicula.findById(peliculaActualizada.getIdPelicula()).isPresent())
				return ResponseEntity.accepted().body("Pelicula Actualizada");
			else
				return ResponseEntity.unprocessableEntity().body("Error al actualizar la pelicula");
		} else
			return ResponseEntity.unprocessableEntity().body("No se encuentra el personaje");
	}

	public ResponseEntity<Object> eliminarPelicula(Long idPelicula) {
		if (repoPelicula.findById(idPelicula).isPresent()) {
			repoPelicula.deleteById(idPelicula);
			if (repoPelicula.findById(idPelicula).isPresent())
				return ResponseEntity.unprocessableEntity().body("Error al eliminar la pelicula");
			else
				return ResponseEntity.ok().body("PElicula eliminada");
		} else
			return ResponseEntity.badRequest().body("No se encuentra la pelicula");
	}

	public ResponseEntity<List<Pelicula>> buscarPorNombre(String nombre) {
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();

			if (nombre == null)
				repoPelicula.findAll().forEach(peliculas::add);
			else
				repoPelicula.findByTitulo(nombre).forEach(peliculas::add);

			if (peliculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(peliculas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Pelicula>> buscarPorGenero(int idGenero) {
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();

			if (idGenero == 0)
				repoPelicula.findAll().forEach(peliculas::add);
			else
				repoPelicula.findByGenero(idGenero).forEach(peliculas::add);

			if (peliculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(peliculas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<List<Pelicula>> buscarPorOrden(String orden) {
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();

			if (orden == null)
				repoPelicula.findAll().forEach(peliculas::add);
			else
				//repoPelicula.findByOrderByTitulo(orden).forEach(peliculas::add);

			if (peliculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(peliculas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
