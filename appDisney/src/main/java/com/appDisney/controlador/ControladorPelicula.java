package com.appDisney.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appDisney.entidad.Pelicula;
import com.appDisney.servicio.ServicioPelicula;

@RestController
public class ControladorPelicula {
	

	private ServicioPelicula servPelicula;

	public ControladorPelicula(ServicioPelicula servPelicula) {
		super();
		this.servPelicula = servPelicula;
	}
	//SE OBTIENEN TODOS LOS PERSONAJES
	@GetMapping("/movies/detail")
	public ArrayList<Pelicula> obtenerPeliculas() {
		return servPelicula.obtenerPeliculas();
	}
	
	//SE CREA UN PERSONAJE
	@PostMapping(value = "/movie/create")
	public ResponseEntity<Object> agregarPelicula(@Valid @RequestBody Pelicula pelicula) {
		return servPelicula.agregarPelicula(pelicula);
	}

	//SE OBTIENE UN PERSONAJE EN PARTICULAR
	@GetMapping("/movie/{id}")
	public ResponseEntity<Pelicula> obtenerPelicula(@PathVariable("id") long idPelicula) {
		return servPelicula.obtenerPelicula(idPelicula);
	}
	
	//SE ACTUALIZA UN PERSONAJE EN PARTICULAR
	@PutMapping("/movie/update/{id}")
	public ResponseEntity<Object> actualizarPelicula(@PathVariable("id") Long idPelicula, @RequestBody Pelicula pelicula) {
		return servPelicula.actualizarPelicula(pelicula, idPelicula);
	}
	
	//SE ELIMINA UN PERSONAJE EN PARTICULAR
	@DeleteMapping("movie/delete/{id}")
	public ResponseEntity<Object> eliminarPelicula(@PathVariable("id") Long idPelicula) {
		return servPelicula.eliminarPelicula(idPelicula);
	}
	
	@GetMapping(value= "/movies", params="name")
	public ResponseEntity<List<Pelicula>> buscarPorNombre(@RequestParam("name") String nombre) {
		return servPelicula.buscarPorNombre(nombre);	
	}
	
	@GetMapping(value= "/movies", params="genre")
	public ResponseEntity<List<Pelicula>> buscarPorGenero(@RequestParam("genre") int idGenero) {
		return servPelicula.buscarPorGenero(idGenero);	
	}
	
	@GetMapping(value= "/movies", params="order")
	public ResponseEntity<List<Pelicula>> buscarPorOrden (@RequestParam("order") String order) {
		return servPelicula.buscarPorOrden(order);	
	}
	

}
