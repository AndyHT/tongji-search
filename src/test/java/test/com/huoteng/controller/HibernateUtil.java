package test.com.huoteng.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
 
	public static final SessionFactory sessionFactory;
	public static final ThreadLocal session = new ThreadLocal();

	static{  
		try{  
			Configuration configuration=new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();  
		}catch (Throwable ex){  
			System.err.println("Initial SessionFactory creation failed." + ex);  
			throw new ExceptionInInitializerError(ex);  
		}  
	}  
	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();  
		if (s == null) {  
			s = sessionFactory.openSession();  
			session.set(s);  
		}  
		return s;  
	}  
 
	public static void closeSession() throws HibernateException {  
		Session s = (Session) session.get();  
		if (s != null) {
			s.close();  
		}
		session.set(null);  
	}  
}