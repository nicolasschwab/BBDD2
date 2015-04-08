package bd2.model;

import java.util.Date;

/**
 * La clase  TareaDeDesarrollo extiende de la clase Tarea y representa las tareas que se estan desarrollando 
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */



public class TareaDeDesarrollo extends Tarea{
	
	/**
	 *  Constructor de la clase TareaDeDesarrollo, setea la descripcion y la fecha limite de la misma 
	 * 	@param descripcion
	 * 		String con la descripcion de la tarea
	 * @param fechaLimite
	 * 		Fecha limite de la tarea
	 * 
	 */
	public TareaDeDesarrollo(String descripcion, Date fechaLimite){
		super(descripcion, fechaLimite);
		this.setRequerimientos("");
	}
	/**
	 *  Constructor vacio de la clase TareaDeDesarrollo
	 * 
	 * 
	 */
	public TareaDeDesarrollo(){
		
	}

	private String requerimientos;
	
	/**
	 * metodo getter que devuelve los requerimientos de la tarea
	 * 
	 * 
	 */
	public String getRequerimientos() {
		return requerimientos;
	}
	/**
	 * metodo setter que asigna los requerimientos a la tarea
	 * @param requerimientos
	 * 		String que especifica los requermientos de la tarea
	 * 
	 */
	public void setRequerimientos(String requerimientos) {
		this.requerimientos = requerimientos;
	}


}
