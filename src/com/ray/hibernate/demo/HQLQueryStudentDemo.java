package com.ray.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Student;

public class HQLQueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			// retrieve all Student object to List
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			System.out.println("\n\nStudent with firstName='Ray'");
			theStudents = session.createQuery("from Student s where s.firstName='Ray'").getResultList();
			
			displayStudents(theStudents);
			
			System.out.println("\n\nStudent with firstName='Ray' OR firstName='Tommy'");
			theStudents = session.createQuery("from Student s where s.firstName='Ray' or s.firstName='Tommy'").getResultList();
			displayStudents(theStudents);
			
			System.out.println("\n\nStudent with firstName LIKE '%Tommy%' ");
			theStudents = session.createQuery("from Student s where s.firstName LIKE '%Tommy%'").getResultList();
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
		
	}

}
