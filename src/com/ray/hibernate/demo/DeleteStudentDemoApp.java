package com.ray.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Student;

public class DeleteStudentDemoApp {
	public static void main(String[] args) {
		
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 3;
			// start transaction
			session.beginTransaction();
			
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("\nDeleting student..." + myStudent);
			
			//myStudent.setEmail("aptech@email.com");
			
			session.delete(myStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		} finally {
			factory.close();
		}
	}
}
