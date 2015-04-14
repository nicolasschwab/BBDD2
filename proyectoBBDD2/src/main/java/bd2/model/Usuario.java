package bd2.model;

/**
 * La clase Usuario que tiene la información de un usuario
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */
public class Usuario {
	/**
	 *  Constructor de la clase Usuario que setea las variables emaily nombre
	 *  
	 * 
	 */
	public Usuario(){
		
	}
	
	public Usuario(String email, String nombre){
		this.setEmail(email);
		this.setNombre(nombre);
	}
	private String email;
	private String nombre;
	private Long idUsuario;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 *  método getter que devuelve el valor de la variable email
	 *  	  
	 * 
	 */

	public String getEmail() {
		return email;
	}
	
	/**
	 *  método setter que setea la variable email
	 * @param email
	 * String con el valor del email
	 * 
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 *  método getter que devuelve el valor de la variable nombre
	 *  	  
	 * 
	 */

	public String getNombre() {
		return nombre;
	}
	
	/**
	 *  método setter que setea la variable nombre
	 * @param nombre
	 * String con el nombre
	 * 
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
