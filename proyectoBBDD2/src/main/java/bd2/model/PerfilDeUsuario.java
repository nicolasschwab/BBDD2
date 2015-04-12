package bd2.model;
//hola nico
import java.util.Date;

/**
 * La clase  PerfilDeUsuario  representa los perfiles de los usuarios de cada proyecto 
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */


public class PerfilDeUsuario {
	
	
	/**
	 *  Constructor de la clase PerfilDeUsuario que setea la fecha de creacion y el usuario al que pertenece
	 * @param fechaDeCreacion 
	 * 		fecha en la cual fue creado el perfil
	 * @param usuario
	 * 		Usuario al cual pertenece el perfil
	 * 
	 */
	public PerfilDeUsuario(){
		
	}
	public PerfilDeUsuario(Date fechaDeCreacion, Usuario usuario){
		this.setUsuario(usuario);
		this.setFechaDeCreacion(fechaDeCreacion);
	}
	private Boolean esCreador=false;
	private Usuario usuario;
	private Date fechaDeCreacion;
	private int idPerfilUsuario;
	
	public int getIdPerfilUsuario() {
		return idPerfilUsuario;
	}
	public void setIdPerfilUsuario(int idPerfilUsuario) {
		this.idPerfilUsuario = idPerfilUsuario;
	}
	/**
	 * metodo getter que devuelve el usuario del perfil
	 * 		
	 * 
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * metodo setter que asigna el usuario al perfil
	 * @param usuario
	 * 		Usuario que se desea asginar al perfil
	 * 
	 */
	public void setUsuario(Usuario usuario) { 
		this.usuario = usuario;
	}
	/**
	 * metodo getter que devuelve el la fecha de creacion del perfil del usuario
	 * 
	 * 
	 */
	public Date getFechaDeCreacion() { // 
		return fechaDeCreacion;
	}
	/**
	 * metodo setter que asigna la fecha de creacion del perfil
	 * @param fechaDeCreacion
	 * 		fecha que se desea asignar a la fecha de creacion
	 * 
	 */
	public void setFechaDeCreacion(Date fechaDeCreacion) { //  
		this.fechaDeCreacion = fechaDeCreacion;
	}
	/**
	 * metodo getter que devuelve si el usuario es el creador del proyecto
	 * 
	 * 
	 */
	public Boolean getEsCreador() { // 
		return esCreador;
	}
	/**
	 * metodo setter que asigna si el usuario es el creador del proyecto
	 * @param esCreador
	 * 		Variable booleana que indica si el usuario es o no creador del proyecto
	 * 
	 */
	public void setEsCreador(Boolean esCreador) { // 
		this.esCreador = esCreador;
	}
	/**
	 * metodo que elimina el perfil de los intgrantes del proyecto
	 * @param proyecto
	 * 		Indica de que proyecto se desea borrar el perfil
	 * 
	 */
	public void eliminarDeProyecto(Proyecto proyecto) throws Exception { 
		proyecto.getIntegrantes().remove(this.getUsuario());
	}
}
