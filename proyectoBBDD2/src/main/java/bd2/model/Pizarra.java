package bd2.model;


import java.util.Collection;
import java.util.HashSet;

/**
 * La clase  Pizarra  maneja las tareas asginadas a cada pizarra
 * 
 * @author Grupo8
 * 
 * @version 1.0
 */
public class Pizarra {
	/**
	 *  Constructor de la clase Pizarra, setea el nombre de la misma
	 * 
	 * 
	 */
	public Pizarra(){
		
	}
	public Pizarra(String nombre){  // 
		this.setNombre(nombre);
	}
	private String nombre;
	private Collection<Tarea> tareas = new HashSet<Tarea>();
	private int idPizarra;
	
	public int getIdPizarra() {
		return idPizarra;
	}
	public void setIdPizarra(int idPizarra) {
		this.idPizarra = idPizarra;
	}
	/**
	 *  metodo getter que devuelve el nombre de la pizarra
	 * 
	 * 
	 */
	public String getNombre() { //
		return nombre;
	}
	/**
	 * metodo setter que asigna el nombre de la pizarra
	 * @param nombre
	 * 		String con el nombre de la pizarra
	 * 
	 */
	public void setNombre(String nombre) { // 
		this.nombre = nombre;
	}
	/**
	 * metodo getter que devuelve  la coleccion de tareas de la pizarra
	 * 
	 * 
	 */
	public Collection<Tarea> getTareas() { // 
		return tareas;
	}
	/**
	 * metodo setter que asigna la coleccion de tareas de la pizarra
	 * @param tareas
	 * 		coleccion de tareas que se desea asignar a la pizarra
	 * 
	 */
	public void setTareas(Collection<Tarea> tareas) { // 
		this.tareas = tareas;
	}
	/**
	 *  metodo que elimina una tarea de la pizarra actual y la mueve a la que entra por parametro
	 * @param tarea
	 * 		tarea que se desea mover
	 * @param pizarraDestino
	 * 		Pizarra a la cual se desea mover la tarea
	 */
	public void moverTareaAPizarra(Tarea tarea, Pizarra pizarraDestino){ //
		if(this.getTareas().contains(tarea)){
			this.getTareas().remove(tarea);
			//pizarraDestino.getTareas().add(tarea);
			tarea.agregarAPizarra(pizarraDestino);
		}
		else{ System.out.println("La tarea no se encuentra en la pizarra");}
	}
	/**
	 * metodo que agrega a la pizarra actual la tarea que entra como parametro
	 * @param tarea
	 * 		tarea que se desea agregar a la pizarra
	 * 
	 */
	public void agregarTarea(Tarea tarea){ // 
		//this.getTareas().add(tarea);
		tarea.agregarAPizarra(this);
	}
}
