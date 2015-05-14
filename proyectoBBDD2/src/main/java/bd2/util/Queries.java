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
		
		buildSessionFactory();
		
//	-----"configuracion deprecated"------
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate/hibernate.cfg.xml");
//		sessions = cfg.buildSessionFactory();
		conexion('a');
		conexion('c');
		conexion('d');
		
	}
	
	private static void conexion(char letraConsulta){
		
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			System.out.println();
			System.out.println("--------------------------------------------------------- interlineado --------------------------------------------------");
			//acá hay que poner un case que dado la letra que entra por parametro es la consulta que llama
			//si bien se que esta forma no es la mejor en el caso de tener 1000 consultas dado que solo 
			// tenemos 8 consultas de estar forma reusamos mas codigo y es todo mas facilito
			switch (letraConsulta){
			case 'a': listarNombresPizarras(session,tx); break;
			case 'c': pizarraConMasTareas(session,tx);break;
			case 'd': mailDeAdminsConPizarraArchivada(session,tx); break;
			}
			System.out.println("----------interlineado--------");
			session.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}
		session.disconnect();
		
	}
	public static void listarNombresPizarras(Session sesion, Transaction t){
		System.out.println("Listar los nombres de todas las pizarras");
		System.out.println("----------interlineado--------");
		List<String> nombres= sesion.createQuery(" select p.nombre from Pizarra p").list();
		for(String unNombre: nombres){
			System.out.println(unNombre);
		}
	}
	
	public static void mailDeAdminsConPizarraArchivada(Session sesion, Transaction t){
		System.out.println("Obtener	los emails de los administradores de los proyectos que tengan al menos una pizarra archivada.");
		System.out.println("----------interlineado--------");
		List<String> coleccion= sesion.createQuery("select usuario.email from PerfilDeAdministrador as PDA where PDA IN "
				+ "(select perf from Proyecto as p inner join p.perfiles as perf where perf.class = PerfilDeAdministrador and p.pizarrasArchivadas.size > 0)").list();
/*		
  		List<String> prueba1= sesion.createQuery("select usuario.email from PerfilDeAdministrador").list();
		for(String iteracion: prueba1){
			System.out.println("Soy Administrador: "+iteracion);
		}
		
		List<String> prueba2= sesion.createQuery("select perfiles.usuario.email from Proyecto p inner join p.perfiles as perfiles where p.idProyecto = 1 or p.idProyecto = 3").list();
		for(String iteracion: prueba2){
			System.out.println("Pertenezco a uno de los proyectos con pizarras archivadas: "+iteracion);
		}
 */
		
		for(String iteracion: coleccion){
			System.out.println("Administrador: "+iteracion);
		}
	}
	
	private static void pizarraConMasTareas(Session sesion, Transaction t){
		System.out.println("Obtener la Pizarra que tenga más tareas");
		System.out.println("----------interlineado--------");		
		Query nombres = sesion.createQuery(" select p,p.tareas.size as cant from Pizarra p order by cant desc");
		nombres.setMaxResults(1);
		Object tarea=nombres.uniqueResult(); 
		 System.out.println("La pizarra con mas tareas es: "+((Pizarra) (((Object[]) tarea)[0])).getNombre()+" con un total de:"+(((Object[]) tarea)[1])+" tareas");
	}
	
}	
