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
	}

	//private Boolean esCreador;
	private int idAdministrador;
	
//	public Boolean getEsCreador() {
//		return esCreador;
//	}


public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
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
		if( this.getEsCreador()){
			System.out.println("No se puede eliminar al creador del proyecto msj desde la clase PerfilDeAdministrador");
			throw new Exception("No se puede eliminar al creador del proyecto");
		}
		else {
			super.eliminarDeProyecto(proyecto);
		}
	}
}
