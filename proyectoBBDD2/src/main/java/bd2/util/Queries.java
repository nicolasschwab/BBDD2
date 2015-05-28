package bd2.util;

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

public class Queries {

	private static SessionFactory sessionFactory;
	private static Session session;

	private static void buildSessionFactory() {
		sessionFactory = new Configuration().configure(
				"hibernate/hibernate.cfg.xml").buildSessionFactory();
	}

	public static void main(String[] args) {

		try {
			buildSessionFactory();
			session = sessionFactory.openSession();
			listarNombresPizarras();
			pizarraConMasTareas();
			mailDeAdminsConPizarraArchivada();
			obtenerTareasQuePasaronPorPizarraConSecuencia("backlogproyecto8149");
			obtenerTareasCambiadasDePizarraMasDeVeces(2);
			session.close();
//			session.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Tarea> obtenerTareasCambiadasDePizarraMasDeVeces(
			Integer cantidad) {

		System.out.println();
		System.out
				.println("--------------------------------------------------------- interlineado --------------------------------------------------");

		System.out
				.println("Obtener las tareas que hayan sido cambiadas de pizarra"
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
				System.out.println("----------interlineado--------");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return tareas;

	}

	public static List<Tarea> obtenerTareasQuePasaronPorPizarraConSecuencia(
			String secuencia) {

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
							"select t from Pizarra p inner join p.tareas t where p.nombre like :nombreDePizarra ")
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

	public static void listarNombresPizarras() {
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

	public static List<String> mailDeAdminsConPizarraArchivada() {
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
		return coleccion;

	}

	private static void pizarraConMasTareas() {
		System.out.println("Obtener la Pizarra que tenga más tareas");
		System.out.println("----------interlineado--------");
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query nombres = session
					.createQuery(" select p,p.tareas.size as cant from Pizarra p order by cant desc");
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

}

