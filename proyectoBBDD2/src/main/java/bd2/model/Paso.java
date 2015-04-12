package bd2.model;

import java.util.Date;

/**
 * La clase  Paso guarda la información de las pizzaras que han sido movidas de lugar
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */


public class Paso {
	/**
	 *  Constructor de la clase Paso
	 * 
	 * 
	 */
	
	public Paso(){
		
	}
	private Date fechaDeIngreso;
	private Pizarra pizarra;
	private  int idPaso;
	
	

	public int getIdPaso() {
		return idPaso;
	}

	public void setIdPaso(int idPaso) {
		this.idPaso = idPaso;
	}

	/**
	 * metodo getter que devuelve los fecha de ingreso de la pizarra que cambió de lugar
	 * 
	 * 
	 */
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	
	/**
	 * metodo setter que asigna la fecha de cambio e la pizarra
	 * @param fechaDeIngreso
	 * 		Date que especifica la fecha de cambio de lugar de la Pizarra
	 * 
	 */
	
	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	/**
	 * metodo getter que devuelve la pizarra que cambió de lugar
	 * 
	 * 
	 */
	public Pizarra getPizarra() {
		return pizarra;
	}
	/**
	 * metodo setter que asigna la pizarra que cambió de lugar
	 * @param pizarra
	 * 		Pizarra que especifica la pizarra que cambió de lugar
	 * 
	 */
	
	public void setPizarra(Pizarra pizarra) {
		this.pizarra = pizarra;
	}
}
