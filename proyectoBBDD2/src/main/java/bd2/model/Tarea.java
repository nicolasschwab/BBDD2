package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
/**
 * La clase  Tarea es una clase abstracta que representa una tarea en una Pizarra
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */



public abstract class Tarea {
	/**
	 *  Constructor de la clase Tarea que setea las variables descripción,fechaLimite y completa
	 * 
	 * 
	 */

	public Tarea(String descripcion, Date fechaLimite){
		this.setDescripcion(descripcion);
		this.setFechaLimite(fechaLimite);
		this.setCompleta(false);
	}
	/**
	 *  Constructor de la clase Tarea
	 * 
	 * 
	 */

	public Tarea(){
		
	}
	private Boolean completa;
	private Date fechaLimite;
	private String descripcion;
	private Collection<Paso> pasos=new ArrayList<Paso>();
	private Long idTarea;
	public Long getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}
	/**
	 *  método agregarAPizarra Se agrega la tarea a la pizarra enviada como parámetro.  
	 * Se registra este movimiendo generando un nuevo Paso y agregándolo a su colección de pasos, 
	 * con la fecha actual y la pizarra en cuestión.
	 * @param pizarra
	 * 	Pizarra a la cual se desea agregar la tarea
	 * 
	 * 
	 */

	public void agregarAPizarra(Pizarra pizarra){
		pizarra.getTareas().add(this);
		Paso p= new Paso();
		p.setFechaDeIngreso(new Date());
		p.setPizarra(pizarra);
		this.getPasos().add(p);
	}

	/**
	 *  método vencida devuelve un boolean que dice si la Tarea esta vencida o no  
	 * 
	 * 
	 */

	public Boolean vencida(){
		Date fechaActual= new Date();
		return this.getFechaLimite().before(fechaActual);
		
	}

	/**
	 *  método completa retorna un boolean con el estado de la Tarea  
	 * 
	 * 
	 */

	public Boolean completa(){
		return this.getCompleta();
		
	}
	/**
	 *  método completar devuelve un boolean en valor True seteando la variable completa también en True  
	 * 
	 * 
	 */

	public Boolean completar(){
		this.setCompleta(true);
		return true;
	}
	
	/**
	 *  método getFechaLimite devuelve un Date con la fechaLimite de la Tarea
	 *  	  
	 * 
	 */

	public Date getFechaLimite() {
		return fechaLimite;
	}
	
	/**
	 *  método setter que setea la fechaLimite de la tarea 
	 * @param fechaLimite
	 * Date que tiene la fecha de límite
	 * 
	 */

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 *  método getter que devuelve el estado de la Tarea 
	 * 
	 * 
	 */

	public Boolean getCompleta() {
		return completa;
	}
	
	/**
	 *  método setter que setea la variable de estado de completo de la Tarea  
	 * @param completa
	 * Boolean con el estado de la Tarea
	 * 
	 */

	public void setCompleta(Boolean completa) {
		this.completa = completa;
	}
	
	/**
	 *  método getter que devuelve la descripción de la Tarea
	 * 
	 * 
	 */

	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 *  método setter que setea la variable de descripción de la Tarea 
	 * @param descripcion
	 * String con la descripción
	 * 
	 */

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 *  método getter Retorna el historial de pasos, donde cada objeto Paso representa la incorporación de la tarea en una pizarra,
	 *  en una fecha dada. 
	 * 
	 */

	public Collection<Paso> getPasos() {
		return pasos;
	}
	
	
	/**
	 *  método setter que setea la variable pasos de la Tarea 
	 * @param pasos
	 * Collection<Paso> con colección de pasos a agregar
	 * 
	 */
	public void setPasos(Collection<Paso> pasos) {
		this.pasos = pasos;
	}

}
