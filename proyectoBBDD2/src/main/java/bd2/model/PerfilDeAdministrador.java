package bd2.model;

import java.util.Date;

/**
 * La clase PerfilDeAdministrador extiende de PerfilDeUsuario y representa los perfiles de los administradores de cada proyecto 
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */


public class PerfilDeAdministrador extends PerfilDeUsuario{

	/**
	 *  Constructor de la clase PerfilDeAdministrador, setea la fecha de creacion y el perfil al cual referencia
	 * @param fechaDeCreacion
	 * 		fecha en la cual fue creado el perfil
	 * @param Usuario
	 * 		Usuario al cual pertenece el perfil
	 * 
	 */
	public PerfilDeAdministrador(){
		
	}
	public PerfilDeAdministrador(Date fechaDeCreacion, Usuario usuario) {
		super(fechaDeCreacion, usuario);
		this.creador=false;
	}

	private Boolean creador;
	private Long idAdministrador;
	
//	public Boolean getEsCreador() {
//		return esCreador;
//	}
	

	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public Boolean getCreador() {
	return creador;
	}
	
	public void setCreador(Boolean creador) {
		this.creador = creador;
	}
	
	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	
	public boolean esCreador(){
		return this.creador;
	}

	//	public void setEsCreador(Boolean esCreador) {
//		this.esCreador = esCreador;
//	}
	/**
	 * Elimina el administrador del proyecto siempre y cuando no sea el creador
	 * @param proyecto 
	 * 		proyecto del cual se desea eliminar al administrador
	 */

	public void eliminarDeProyecto(Proyecto proyecto) throws Exception{
		if( this.esCreador()){
			System.out.println("No se puede eliminar al creador del proyecto msj desde la clase PerfilDeAdministrador");
			throw new Exception("No se puede eliminar al creador del proyecto");
		}
		else {
			proyecto.eliminarPerfil(this);
		}
	}
	
	/**
	 * Este metodo es usado para corroborar si el proyecto ya posee o no un perfil con el usuario que le entra como parametro
	 * 
	 */
	public boolean esColaborador() {
		// TODO Auto-generated method stub
		return false;
	}
}
