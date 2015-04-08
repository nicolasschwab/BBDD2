package bd2.model;

import java.util.Date;
/**
 * La clase TareaDeInvestigacion que extiende de la clase Tarea
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */


public class TareaDeInvestigacion extends Tarea {
	/**
	 *  Constructor de la clase TareaDeInvestigacion que setea las variables hipotesis y resultado y 
	 * utiliza el constructor de la clase super parra setear las variables descripcion y fechaLimite
	 * 
	 */

	public TareaDeInvestigacion(String descripcion, Date fechaLimite){
		super(descripcion,fechaLimite);
		this.setHipotesis("");
		this.setResultado("");
	}
	/**
	 *  Constructor de la clase TareaDeInvestigacion
	 * 
	 * 
	 */

	public TareaDeInvestigacion(){
		
	}
	private String hipotesis;
	private String resultado;

	
	/**
	 *  método getter que devuelve el valor de la variable Hipotesis
	 *  	  
	 * 
	 */

	public String getHipotesis() {
		return hipotesis;
	}

	/**
	 *  método setter que setea la variable hipótesis
	 * @param hipotesis
	 * String con el valor de la hipotesis
	 * 
	 */

	public void setHipotesis(String hipotesis) {
		this.hipotesis = hipotesis;
	}

	/**
	 *  método getter que devuelve el valor de la variable resultado
	 *  	  
	 * 
	 */

	public String getResultado() {
		return resultado;
	}

	/**
	 *  método setter que setea el valor de la variable resultado
	 *  @param resultado
	 *  String con el valor del resultado
	 *  	  
	 * 
	 */

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
