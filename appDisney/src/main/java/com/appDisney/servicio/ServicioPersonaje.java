package com.appDisney.servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.appDisney.entidad.Pelicula;
import com.appDisney.entidad.Personaje;
import com.appDisney.repositorio.RepositorioGenero;
import com.appDisney.repositorio.RepositorioPelicula;
import com.appDisney.repositorio.RepositorioPersonaje;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Optional;

@Service
public class ServicioPersonaje {
	private RepositorioPersonaje repoPersonaje;
	private RepositorioPelicula repoPelicula;

	public ServicioPersonaje(RepositorioPersonaje repoPersonaje, RepositorioPelicula repoPelicula) {
		super();
		this.repoPersonaje = repoPersonaje;
		this.repoPelicula = repoPelicula;
	}

	public ResponseEntity<Object> agregarPersonaje(Personaje personaje) {

		Personaje per = new Personaje();

		per.setImagen(personaje.getImagen());
		per.setNombre(personaje.getNombre());
		per.setEdad(personaje.getEdad());
		per.setPeso(personaje.getPeso());
		per.setHistoria(personaje.getHistoria());

		per.setPeliculas(personaje.getPeliculas());

		Personaje nuevoPersonaje = repoPersonaje.save(per);

		if (repoPersonaje.findById(nuevoPersonaje.getIdPersonaje()).isPresent())
			return ResponseEntity.ok("Personaje creado!");
		else
			return ResponseEntity.unprocessableEntity().body("Error al crear el personaje");

	}

	public List<Map<String, Object>> obtenerPersonajes(){
		
		List<Personaje> personajes = repoPersonaje.findAll();
		
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		for (Personaje personaje : personajes) {
			Map<String, Object> mapPersonajes = new HashMap<String, Object>();
			mapPersonajes.put("Imagen", personaje.getImagen());
			mapPersonajes.put("Nombre", personaje.getNombre());
			
			resultado.add(mapPersonajes);
		}
		return resultado;
		
	}
	
	public List<Personaje> obtenerDetallePersonajes() {
		List<Personaje> personajes = repoPersonaje.findAll();

		return personajes;
	}

	public ResponseEntity<Personaje> obtenerPersonaje(long idPersonaje) {
		Optional<Personaje> personaje = repoPersonaje.findById(idPersonaje);

		if (personaje.isPresent()) {
			return new ResponseEntity<>(personaje.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	public ResponseEntity<Object> actualizarPersonaje(Personaje personaje, Long idPersonaje) {
		if (repoPersonaje.findById(idPersonaje).isPresent()) {
			Personaje nuevoPersonaje = repoPersonaje.findById(idPersonaje).get();
			nuevoPersonaje.setEdad(personaje.getEdad());
			nuevoPersonaje.setHistoria(personaje.getHistoria());
			nuevoPersonaje.setImagen(personaje.getImagen());
			nuevoPersonaje.setNombre(personaje.getNombre());
			nuevoPersonaje.setPeso(personaje.getPeso());
			nuevoPersonaje.setPeliculas(personaje.getPeliculas());
			Personaje personajeActualizado = repoPersonaje.save(nuevoPersonaje);
			if (repoPersonaje.findById(personajeActualizado.getIdPersonaje()).isPresent())
				return ResponseEntity.accepted().body("Personaje Actualizado");
			else
				return ResponseEntity.unprocessableEntity().body("Error al actualizar el personaje");
		} else
			return ResponseEntity.unprocessableEntity().body("No se encuentra el personaje");
	}

	public ResponseEntity<Object> eliminarPersonaje(Long idPersonaje) {
		if (repoPersonaje.findById(idPersonaje).isPresent()) {
			repoPersonaje.deleteById(idPersonaje);
			if (repoPersonaje.findById(idPersonaje).isPresent())
				return ResponseEntity.unprocessableEntity().body("Error al eliminar el personaje");
			else
				return ResponseEntity.ok().body("Personaje eliminado");
		} else
			return ResponseEntity.badRequest().body("No se encuentra el personaje");
	}

	public ResponseEntity<List<Personaje>> buscarPorNombre(String nombre) {
		try {
			List<Personaje> personajes = new ArrayList<Personaje>();

			if (nombre == null)
				repoPersonaje.findAll().forEach(personajes::add);
			else
				repoPersonaje.findByNombre(nombre).forEach(personajes::add);

			if (personajes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(personajes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Personaje>> buscarPorEdad(int edad) {
		try {
			List<Personaje> personajes = new ArrayList<Personaje>();

			if (edad == 0)
				repoPersonaje.findAll().forEach(personajes::add);
			else
				repoPersonaje.findByEdad(edad).forEach(personajes::add);

			if (personajes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(personajes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Personaje>> buscarPorPelicula(long idPelicula) {
		try {
			List<Personaje> personajes = new ArrayList<Personaje>();

			Optional<Pelicula> pelicula = repoPelicula.findById(idPelicula);

			for (Personaje peli : pelicula.get().getPersonajes()) {
				personajes.add(peli);
			}

			if (personajes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(personajes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	public Personaje obtenerDetallePersonaje(long idPersonaje) {
        List<Pelicula> peliculaList = repoPelicula.findAll();
		
		Personaje personaje = repoPersonaje.findById(idPersonaje).get();
		Personaje per = new Personaje();
		per.setIdPersonaje(personaje.getIdPersonaje());
		per.setEdad(personaje.getEdad());
		per.setHistoria(personaje.getHistoria());
		per.setImagen(personaje.getImagen());
		per.setNombre(personaje.getNombre());
		per.setPeso(personaje.getPeso());
		per.setPeliculas(getPeliculaList(personaje));
		
        /*if(peliculaList.size()>0) {
            List<Pelicula> peliculas = new ArrayList<>();
            for (Pelicula peli : peliculaList) {
                Pelicula model = new Pelicula();
                model.setTitulo(peli.getTitulo());
                model.setCalificacion(peli.getCalificacion());
                model.setIdPelicula(peli.getIdPelicula());
                model.setFechaCreacion(peli.getFechaCreacion());
                model.setPersonajes(getPersonajeList(peli));
                
               
                peliculas.add(model);
            }*/
            return per;
        
    } 
	
    public List<Pelicula> getPeliculaList(Personaje personaje){
        List<Pelicula> peliculaList = new ArrayList<>();
        
        for(int i=0; i< personaje.getPeliculas().size(); i++) {
        	
            Pelicula peli = new Pelicula();
            peli.setIdPelicula(personaje.getPeliculas().get(i).getIdPelicula());
            peli.setCalificacion(personaje.getPeliculas().get(i).getCalificacion());
            peli.setFechaCreacion(personaje.getPeliculas().get(i).getFechaCreacion());
            //peli.setGenero(personaje.getPeliculas().get(i).getGenero());
            peli.setImagen(personaje.getPeliculas().get(i).getImagen());
            peli.setTitulo(personaje.getPeliculas().get(i).getTitulo());
            peliculaList.add(peli);
        }
        return peliculaList;
    }
	
	
	
	

}
