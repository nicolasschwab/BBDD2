package bd2.model;


import java.util.Collection;
import java.util.HashSet;
/**
 * La clase  Sitio que administra los proyectos junto a su administrador y usuarios
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */

public class Sitio {

	/**
	 *  Constructor de la clase Sitio
	 * 
	 * 
	 */

	public Sitio(){
		
	}
	private Collection<Usuario> usuarios= new HashSet<Usuario>();
	private Collection<Proyecto> proyectos = new HashSet<Proyecto>();
	private int idSitio;
	public int getIdSitio() {
		return idSitio;
	}

	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}

	/**
	 * metodo registrarUsuario que agrega un nuevo usuario al proyecto
	 * @param usuario
	 * 		Usuario usuario que se agrega al proyecto
	 * 
	 */

	public void registrarUsuario(Usuario usuario){
		if(!this.getUsuarios().contains(usuario)){
		this.getUsuarios().add(usuario);}
	}
	
	/**
	 * metodo agregarProyecto agrega el proyecto a trabajar
	 * @param proyecto
	 * 		Proyecto posee el proyecto
	 * 
	 */

	public void agregarProyecto(Proyecto proyecto){
		this.getProyectos().add(proyecto);
	}
	
	/**
	 * metodo getter que devuelve los usuarios del proyecto
	 * 
	 */

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * metodo setter que asigna una colección de usuarios al Sitio
	 * @param usuarios
	 * 		Collection <Usuario> posee la coleccion de usuarios a agregar
	 * 
	 */

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * metodo getter que devuelve la colección de proyectos que posee el sitio
	 * 
	 */

	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}
	/**
	 * metodo setter que asigna una colección de proyectos al Sitio
	 * @param proyectos
	 * 		Collection<Proyectos> poseen la coleccion de Proyectos a agregar
	 * 
	 */

	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
}
