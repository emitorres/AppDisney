package com.appDisney.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.appDisney.entidad.Personaje;
import com.appDisney.servicio.ServicioPersonaje;
import com.google.common.collect.Multimap;


@RestController
public class ControladorPersonaje {

	private ServicioPersonaje servPersonaje;

	public ControladorPersonaje(ServicioPersonaje servPersonaje) {
		super();
		this.servPersonaje = servPersonaje;
	}
	//SE OBTIENEN TODOS LOS PERSONAJES
	@GetMapping("/characters/detail")
	public List<Personaje> obtenerDetallePersonajes() {
		return servPersonaje.obtenerDetallePersonajes();
	}
	
	

	
	//SE CREA UN PERSONAJE
	@PostMapping(value = "/character/create")
	public ResponseEntity<Object> agregarPersonaje(@Valid @RequestBody Personaje personaje) {
		return servPersonaje.agregarPersonaje(personaje);
	}

	//SE OBTIENE UN PERSONAJE EN PARTICULAR
	@GetMapping("/character/{id}")
	public ResponseEntity<Personaje> obtenerPersonaje(@PathVariable("id") long idPersonaje) {
		return servPersonaje.obtenerPersonaje(idPersonaje);
	}
	
	//SE ACTUALIZA UN PERSONAJE EN PARTICULAR
	@PutMapping("/character/update/{id}")
	public ResponseEntity<Object> actualizarPersonaje(@PathVariable("id") Long idPersonaje, @RequestBody Personaje personaje) {
		return servPersonaje.actualizarPersonaje(personaje, idPersonaje);
	}
	
	//SE ELIMINA UN PERSONAJE EN PARTICULAR
	@DeleteMapping("character/delete/{id}")
	public ResponseEntity<Object> eliminarPersonaje(@PathVariable("id") Long idPersonaje) {
		return servPersonaje.eliminarPersonaje(idPersonaje);
	}
	
	@GetMapping(value= "/characters", params="name")
	public ResponseEntity<List<Personaje>> buscarPorNombre(@RequestParam("name") String nombre) {
		return servPersonaje.buscarPorNombre(nombre);	
	}
	
	@GetMapping(value= "/characters", params="age")
	public ResponseEntity<List<Personaje>> buscarPorEdad(@RequestParam("age") int edad) {
		return servPersonaje.buscarPorEdad(edad);	
	}
	
	@GetMapping(value= "/characters", params="movies")
	public ResponseEntity<List<Personaje>> buscarPorPelicula(@RequestParam("movies") long idPelicula) {
		return servPersonaje.buscarPorPelicula(idPelicula);	
	}
	
	
	@GetMapping(value= "/characters")
	public List<Map<String, Object>> obtenerPersonajes(){
		return servPersonaje.obtenerPersonajes();	
	}
}
