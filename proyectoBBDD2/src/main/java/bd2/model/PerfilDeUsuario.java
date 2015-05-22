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
	
	protected Usuario usuario;
	protected Date fechaDeCreacion;
	protected Long idPerfilUsuario;
	
	protected Long getIdPerfilUsuario() {
		return idPerfilUsuario;
	}
	protected void setIdPerfilUsuario(Long idPerfilUsuario) {
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
	 * metodo esCreador que retorna si el perfil de usuario pertenece al creador del proyecto
	 * 
	 * 
	 */
	public boolean esCreador(){
		return false;
	}
	
	/**
	 * metodo que elimina el perfil de los intgrantes del proyecto
	 * @param proyecto
	 * 		Indica de que proyecto se desea borrar el perfil
	 * 
	 */
	public void eliminarDeProyecto(Proyecto proyecto) throws Exception { 
		
		if(this.esCreador()){
			System.out.println("No se puede eliminar al creador del proyecto msj desde la clase PerfilDeUsuario");  // Es imposible que el usuario seal el admin , pero pongo el if por las dudas
			throw new Exception("No se puede eliminar al creador del proyecto");
		}else{
			proyecto.eliminarPerfil(this);
		}
		
	}
	
	/**
	 * Este metodo es usado para corroborar si el proyecto ya posee o no un perfil con el usuario que le entra como parametro
	 * 
	 */
	public boolean esColaborador() {
		// TODO Auto-generated method stub
		return true;
	}
}
