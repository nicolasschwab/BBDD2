package bd2.model;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * La clase  Proyecto  maneja los usuarios, las pizarras de cada proyecto 
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */



public class Proyecto {
	/**
	 *  Constructor de la clase Proyecto que setea el usuario creador del mismo
	 *  @param creador
	 * 		Usuario que se desea asignar como creador del proyecto
	 * 
	 */
	public Proyecto(){
		
	}
	
	public Proyecto(Usuario creador){ // 
		PerfilDeAdministrador pa = new PerfilDeAdministrador(new Date(), creador);
		pa.setEsCreador(true);
	//	this.setCreador(creador);
	//	this.getIntegrantes().add(creador);
		this.getPerfiles().add(pa);
	}
	private Collection<PerfilDeUsuario> perfiles=new HashSet<PerfilDeUsuario>();
	private Collection<Pizarra> pizarras=new HashSet<Pizarra>();
	private Collection<Pizarra>pizarrasArchivadas=new HashSet<Pizarra>();
	private int idProyecto;
	//private Collection<Usuario> integrantes=new HashSet<Usuario>();
	//private Usuario creador;
	
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	/**
	 *metodo getter que devuelve los perfiles asosiados al proyecto
	 * 
	 * 
	 */
	public Collection<PerfilDeUsuario> getPerfiles() { // 
		return perfiles;
	}
	/**
	 * metodo setter que asigna los perfiles asociados al proyecto
	 * @param perfiles
	 * 		Coleccion de perfiles que se desea asignar al proyecto
	 * 
	 */
	public void setPerfiles(Collection<PerfilDeUsuario> perfiles) { 
		this.perfiles = perfiles;
	}
	/**
	 * metodo getter que devuelve las pizarras del proyecto
	 * 
	 * 
	 */
	public Collection<Pizarra> getPizarras() { // 
		return pizarras;
	}
	/**
	 * metodo setter que asigna las pizarras asociadas al proyecyo
	 * @param pizarras
	 * 		coleccion de pizarras que se desea asignar al proyecto
	 * 
	 */
	public void setPizarras(Collection<Pizarra> pizarras) {
		this.pizarras = pizarras;
	}
	/**
	 * metodo getter que devulve las pizarras archivadas del proyecto
	 * 
	 * 
	 */
	public Collection<Pizarra> getPizarrasArchivadas() {
		return pizarrasArchivadas;
	}
	/**
	 * metodo setter que asigna pizarras archivas al proyecto
	 * @param pizarrasArchivadas
	 * 		coleccion de pizarras archivadas que se desea asignar al proyecto
	 * 
	 */
	public void setPizarrasArchivadas(Collection<Pizarra> pizarrasArchivadas) {
		this.pizarrasArchivadas = pizarrasArchivadas;
	}
	/**
	 * metodo getter que devuelve los integrantes del proyecto 
	 * 
	 * 
	 */
	public Collection<Usuario> getIntegrantes() {
		java.util.Iterator<PerfilDeUsuario> i = this.getPerfiles().iterator();
		Collection<Usuario> integrantes=new HashSet<Usuario>();
		while(i.hasNext()){
			PerfilDeUsuario us = i.next();
			integrantes.add(us.getUsuario());
			}
		return integrantes;
	}
 /*	/**
	 * metodo setter que asigna los integrantes del proyecto
	 * @param integrantes
	 * 		coleccion de integrantes que se desean asignar al proyecto
	 * 
	 */
	/* public void setIntegrantes(Collection<Usuario> integrantes) {
		this.integrantes = integrantes;
	} */
	/**
	 * metodo que agrega un colaborador al proyecto
	 * @param colaborador
	 * 		Usuario que se desea agregar como colaborador
	 * 
	 */
	public void agregarColaborador(Usuario colaborador){
		PerfilDeUsuario perfilU= new PerfilDeUsuario(new Date(), colaborador);
		this.getPerfiles().add(perfilU);
		//this.getIntegrantes().add(colaborador);
	}
	/**
	 * metodo que agrega un administrador al proyecto
	 * @param administrador
	 * 		Usuario que se desea agregar como administrador
	 * 
	 */
	public void agregarAdministrador(Usuario administrador){
		PerfilDeAdministrador perfilA= new PerfilDeAdministrador(new Date(), administrador);
		this.getPerfiles().add(perfilA);
		//this.getIntegrantes().add(administrador);
	}
	/**
	 * metodo que elimina un usuario del proyecto
	 * @param pusuario
	 * 		Usuario que se desea eliminar del proyecto
	 * 
	 */
	public void eliminarUsuario(Usuario pusuario)throws Exception {
		//En realidad es "eliminar perfil de usuario, pero en el test le pusieron este nombre..."
		try {
			java.util.Iterator<PerfilDeUsuario> i = this.getPerfiles().iterator();
			boolean encontrado=false;
			while(i.hasNext()&& encontrado==false){
				PerfilDeUsuario us = i.next();
				if(us.getUsuario().equals(pusuario)){
					us.eliminarDeProyecto(this);
					this.getPerfiles().remove(us);
					
					encontrado=true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("No se puede eliminar al creador del proyecto mensaje desde la clase Proyecto");
			throw new Exception("No se puede eliminar al creador del proyecto");
		}
		
	}
	/**
	 * metodo que archiva una pizarra al proyecto
	 * @param pizarra
	 * 		Pizarra que se desea archivar en el proyecto
	 * 
	 */
	public void archivarPizarra(Pizarra pizarra){
		this.getPizarrasArchivadas().add(pizarra);
		this.getPizarras().remove(pizarra);
	}
	/**
	 * metodo que agrega una pizarra al proyecto
	 * @param pizarra
	 * 		Pizarra que se desea agregar al proyecto
	 * 
	 */
	public void agregarPizarra(Pizarra pizarra){
		this.getPizarras().add(pizarra);
	}
	/**
	 * metodo getter que devuelve el creador del proyecto
	 * 
	 * 
	 */
	public Usuario getCreador() {
		java.util.Iterator<PerfilDeUsuario> i = this.getPerfiles().iterator();
		boolean encontrado=false;
		Usuario elCreador=null;
		while(i.hasNext()&& encontrado==false){
			PerfilDeUsuario us = i.next();
			if(us.getEsCreador()){
				elCreador=us.getUsuario();
			}
		}
		return elCreador;
	}
	

}
