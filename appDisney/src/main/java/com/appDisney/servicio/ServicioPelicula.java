package com.appDisney.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appDisney.entidad.Genero;
import com.appDisney.entidad.Pelicula;
import com.appDisney.entidad.Personaje;
import com.appDisney.modelo.ModeloPelicula;
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

	// @Transactional
	public ResponseEntity<Object> agregarPelicula(Pelicula pelicula) {

		Pelicula peli = new Pelicula();

		peli.setCalificacion(pelicula.getCalificacion());
		peli.setFechaCreacion(pelicula.getFechaCreacion());
		peli.setImagen(pelicula.getImagen());
		peli.setTitulo(pelicula.getTitulo());

		peli.setGenero(pelicula.getGenero());

		Pelicula nuevaPelicula = repoPelicula.save(peli);

		if (repoPelicula.findById(nuevaPelicula.getIdPelicula()).isPresent())
			return ResponseEntity.ok("Pelicula creada!");
		else
			return ResponseEntity.unprocessableEntity().body("Error al crear la pelicula");

	}
 

	/*public List<Pelicula> obtenerPeliculas() {
        List<Pelicula> peliculaList = repoPelicula.findAll();
        if(peliculaList.size()>0) {
            List<Pelicula> peliculas = new ArrayList<>();
            for (Pelicula peli : peliculaList) {
                Pelicula model = new Pelicula();
                model.setTitulo(peli.getTitulo());
                model.setCalificacion(peli.getCalificacion());
                model.setIdPelicula(peli.getIdPelicula());
                model.setFechaCreacion(peli.getFechaCreacion());
                model.setPersonajes(getPersonajeList(peli));
                
               
                peliculas.add(model);
            }
            return peliculas;
        } else return new ArrayList<Pelicula>();
    }*/
	
public List<Map<String, Object>> obtenerPeliculas(){
		
		List<Pelicula> peliculas = repoPelicula.findAll();
		
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		for (Pelicula pelicula : peliculas) {
			Map<String, Object> mapPeliculas = new HashMap<String, Object>();
			mapPeliculas.put("Imagen", pelicula.getImagen());
			mapPeliculas.put("Titulo", pelicula.getTitulo());
			mapPeliculas.put("Fecha de Creacion", pelicula.getFechaCreacion());
			
			resultado.add(mapPeliculas);
		}
		return resultado;
		
	}
    public List<Personaje> getPersonajeList(Pelicula peli){
        List<Personaje> personajeList = new ArrayList<>();
        
        for(int i=0; i< peli.getPersonajes().size(); i++) {
            Personaje personajeModel = new Personaje();
            personajeModel.setNombre(peli.getPersonajes().get(i).getNombre());
            personajeModel.setEdad(peli.getPersonajes().get(i).getEdad());
            personajeModel.setHistoria(peli.getPersonajes().get(i).getHistoria());
            personajeModel.setImagen(peli.getPersonajes().get(i).getImagen());
            personajeModel.setPeso(peli.getPersonajes().get(i).getPeso());
            personajeModel.setIdPersonaje(peli.getPersonajes().get(i).getIdPersonaje());
            personajeList.add(personajeModel);
        }
        return personajeList;
    }
	
	
	public ResponseEntity<Pelicula> obtenerPelicula(long idPelicula) {

		if (repoPelicula.findById(idPelicula).isPresent()) {
			Pelicula pelicula = repoPelicula.findById(idPelicula).get();
			Pelicula peli = new Pelicula();

			peli.setCalificacion(pelicula.getCalificacion());
			peli.setFechaCreacion(pelicula.getFechaCreacion());
			peli.setImagen(pelicula.getImagen());
			peli.setTitulo(pelicula.getTitulo());

			return new ResponseEntity<>((Pelicula) peli, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public Pelicula getUsers(long idPelicula) {
       Pelicula pelicula = repoPelicula.findById(idPelicula).get();
        
                Pelicula model = new Pelicula();
                
                model.setTitulo(pelicula.getTitulo());
                model.setImagen(pelicula.getImagen());
                model.setCalificacion(pelicula.getCalificacion());
                model.setFechaCreacion(pelicula.getFechaCreacion());
                model.setIdPelicula(pelicula.getIdPelicula());
                //model.setGenero(pelicula.getGenero());
               model.setPersonajes(pelicula.getPersonajes());
               
                return model; 
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

	public ResponseEntity<List<Pelicula>> buscarPorTitulo(String nombre) {
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

	/*public ResponseEntity<List<Pelicula>> buscarPorGenero(long idGenero) {
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();

			if (idGenero == 0)
				repoPelicula.findAll().forEach(peliculas::add);
			else
		//repoPelicula.findBy(idGenero).forEach(peliculas::add);

			if (peliculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(peliculas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	public ResponseEntity<List<Pelicula>> buscarPorGenero(long idGenero) {
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();

			Optional<Genero> genero = repoGenero.findById(idGenero);

			for (Pelicula peli :genero.get().getPeliculas()) {
				peliculas.add(peli);
			}

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
			// repoPelicula.findByOrderByTitulo(orden).forEach(peliculas::add);

			if (peliculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(peliculas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
