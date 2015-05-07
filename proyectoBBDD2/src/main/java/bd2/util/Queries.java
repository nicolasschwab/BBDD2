package bd2.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Queries {
	
	private static SessionFactory sessions;
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
		conexion('a');
	}
	
	private static void conexion(char letraConsulta){
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("----------interlineado--------");
			//acá hay que poner un case que dado la letra que entra por parametro es la consulta que llama
			//si bien se que esta forma no es la mejor en el caso de tener 1000 consultas dado que solo 
			// tenemos 8 consultas de estar forma reusamos mas codigo y es todo mas facilito
			switch (letraConsulta){
			case 'a': listarNombresPizarras(session,tx);
				break;
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
}	
