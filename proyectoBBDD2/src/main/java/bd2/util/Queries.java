package bd2.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import bd2.model.PerfilDeAdministrador;
import bd2.model.PerfilDeUsuario;
import bd2.model.Pizarra;
import bd2.model.Tarea;
/**
 * 
 * Clase que realiza las Consultas a la Base de Datos de la Etapa 3
 *
 */
public class Queries {

	private static SessionFactory sessionFactory;
	private static Session session;

	private static void buildSessionFactory() {
		sessionFactory = new Configuration().configure(
				"hibernate/hibernate.cfg.xml").buildSessionFactory();
	}
/**
 * Método main que llama a las consultas de clase Queries
 * @param args
 */
	public static void main(String[] args) {

		try {
			buildSessionFactory();
			session = sessionFactory.openSession();
			listarNombresPizarras();
			tareasConDescripcionEspecifica("news");
			pizarraConMasTareas();
			mailDeAdminsConPizarraArchivada();
			obtenerTareasQuePasaronPorPizarraConSecuencia("backlogproyecto8149");
			obtenerTareasCambiadasDePizarraMasDeVeces(2);
			pizarrasConTareaDeInvestigacionYDesarrollo();
			pizarrasConTareasVencidas();
			
			session.close();
//			session.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Consulta que devuelve las tareas que hayan sido cambiadas de pizzarra
	 * 		
	 * 
	 */

	public static List<Tarea> obtenerTareasCambiadasDePizarraMasDeVeces(
			Integer cantidad) {

		System.out.println();
		System.out
				.println("--------------------------------------------------------- interlineado --------------------------------------------------");

		System.out
				.println("Obtener las tareas que hayan sido cambiadas de pizarra "
						+ "mas de un numero de veces enviado como parámetro. Imprimir: Tarea: descripcion (cantidadDePasos)");
		System.out.println("----------interlineado--------");
		Transaction tx = null;
		List<Tarea> tareas = null;
		try {
			tx = session.beginTransaction();
			tareas = session
					.createQuery(
							"select t from Tarea t where t.pasos.size > :cantidad ")
					.setParameter("cantidad", cantidad).list();

			tx.rollback();

			for (Tarea tarea : tareas) {
				System.out.println("Tarea: " + tarea.getDescripcion() + "("
						+ tarea.getPasos().size() + ")");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return tareas;

	}
	/**
	 * Consulta que devuelve las tareas que hayan sido pasadas por la pizarra cuyo nombre contenga una secuencia de caracteres obtenida como parámetro
	 * 	@param secuencia 
	 * 		String enviado como parámetro
	 */

	public static List<Tarea> obtenerTareasQuePasaronPorPizarraConSecuencia(
			String secuencia) {
		System.out.println("----------interlineado--------");
		System.out
				.println("Obtener las tareas que hayan pasado por la pizarra cuyo nombre contenga una"
						+ "secuencia de caracteres obtenida como parámetro. Imprimir Tarea: Descripción");
		System.out.println("----------interlineado--------");
		String parametroConFormato = "%" + secuencia + "%";
		List<Tarea> tareas = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tareas = session
					.createQuery(
							"select t from Pizarra p inner join p.tareas t inner join t.pasos ps where ps.pizarra.nombre like :nombreDePizarra ")
					.setParameter("nombreDePizarra", parametroConFormato)
					.list();

			tx.rollback();

			for (Tarea tarea : tareas) {
				System.out.println("Tarea: " + tarea.getDescripcion());
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}

		return tareas;
	}
	/**
	 * Consulta que devuelve una lista con los nombres de todas las pizarras
	 * 	
	 * 		
	 */
	public static void listarNombresPizarras() {
		System.out.println("----------interlineado--------");
		System.out.println("Listar los nombres de todas las pizarras");
		System.out.println("----------interlineado--------");
		List<String> nombres = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			nombres = session.createQuery(" select p.nombre from Pizarra p")
					.list();
			
			tx.rollback();

			for (String unNombre : nombres) {
				System.out.println("Pizarra: " + unNombre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}

	}
	
	
	/**
	 * Consulta que devuelve los emails de los administradores de los proyectos que tengan al menos una pizarra archivada
	 * 	
	 * 		
	 */
	public static void mailDeAdminsConPizarraArchivada() {
		System.out.println("----------interlineado--------");
		System.out
				.println("Obtener	los emails de los administradores de los proyectos que tengan al menos una pizarra archivada.");
		System.out.println("----------interlineado--------");

		Transaction tx = null;
		List<String> coleccion = null;
		try {
			tx = session.beginTransaction();
			coleccion = session
					.createQuery(
							"select usuario.email from PerfilDeAdministrador as PDA where PDA IN "
									+ "(select perf from Proyecto as p inner join p.perfiles as perf where perf.class = PerfilDeAdministrador and p.pizarrasArchivadas.size > 0)")
					.list();
			
			tx.rollback();

			for (String iteracion : coleccion) {
				System.out.println("Administrador: " + iteracion);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		

	}
	/**
	 * Consulta que devuelve la pizarra con mas tareas
	 * 	
	 * 		
	 */
	private static void pizarraConMasTareas() {
		System.out.println("----------interlineado--------");
		System.out.println("Obtener la Pizarra que tenga más tareas");
		System.out.println("----------interlineado--------");
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query nombres = session.createQuery(" select p,p.tareas.size as cant from Pizarra p order by cant desc");
			nombres.setMaxResults(1);
			Object tarea = nombres.uniqueResult();

			tx.rollback();
			System.out
					.println("La pizarra con mas tareas es: "
							+ ((Pizarra) (((Object[]) tarea)[0])).getNombre()
							+ " con un total de:" + (((Object[]) tarea)[1])
							+ " tareas");
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}

	}
	/**
	 * Consulta que devuelve una lista cuya descripción contenga una secuencia fija de caracteres dado por parámetro
	 * 	@param parametroDescripcion 
	 * 		String enviado como parámetro
	 */
	public static void tareasConDescripcionEspecifica (String parametroDescripcion){
		System.out.println("----------interlineado--------");
		System.out.println("Listar las tareas cuya descripción contenga una secuencia específica de caracteres dado por el parámetro "+parametroDescripcion);
		System.out.println("----------interlineado--------");
		Transaction tx = null;
		List <String> nombres = null;
		try
		{
			tx = session.beginTransaction();
			 nombres = session.createQuery(" select t.descripcion  from Tarea t where t.descripcion like :descripcion")
									.setParameter("descripcion", "%"+parametroDescripcion+"%")
									.list();

			tx.rollback();
			for (String unNombre : nombres) {
				System.out.println("Tarea: " + unNombre);
		
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}
	/**
	 * Consulta que devuelve una lista con las pizarras que tengan tanto tarea de investigación como de desarrollo
	 * 	
	 * 		
	 */
	public static void pizarrasConTareaDeInvestigacionYDesarrollo (){
		System.out.println("----------interlineado--------");
		System.out.println("Listar las pizarras que tengan tareas tanto de investigación como de desarrollo");
		System.out.println("----------interlineado--------");
		Transaction tx = null;
		
		try
		{
			tx = session.beginTransaction();
			List<String> nombres = session.createQuery("select p.nombre from Pizarra p  where p IN "+"(select p1 from Pizarra p1 where p1 IN"+"(select pi from Pizarra pi inner join pi.tareas t where t.class = TareaDeInvestigacion)) and p IN "+"(select p2 from Pizarra p2 where p2 IN"+"(select pi1 from Pizarra pi1 inner join pi1.tareas t where t.class = TareaDeDesarrollo))").list();
			tx.rollback();
			for (String unNombre : nombres) {
				System.out.println("Pizarra: " + unNombre);
		
			}	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}
	/**
	 * Consulta que devuelve una lista con las pizarras que tengan tareas vencidas en marzo
	 * 	
	 * 
	 */
	public static void pizarrasConTareasVencidas (){
		System.out.println("----------interlineado--------");
		System.out.println("Obtener las pizarras que tengan tareas vencidas en marzo");
		System.out.println("----------interlineado--------");
		Transaction tx = null;
		
		try
		{	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date finish= format.parse("2015-03-31");
			Date start=format.parse("2015-03-01");
			
			tx = session.beginTransaction();
			List<String> nombres = session.createQuery("select distinct  p.nombre from Pizarra p, Tarea t where t.completa=false and t.fechaLimite between :comienzo and :final and t in elements(p.tareas)")
					.setParameter("comienzo",start)
					.setParameter("final", finish)
					.list();
			tx.rollback();
			for (String unNombre : nombres) {
				System.out.println("Pizarra: " + unNombre);
		
			}	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}

