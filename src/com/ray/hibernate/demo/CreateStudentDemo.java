package com.ray.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) throws ParseException {
		
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a student
			System.out.println("Create a student");
//			Student tempStudent2 = new Student("Ray2", "Le2", "ray2@email.com");
//			Student tempStudent3 = new Student("Ray3", "Le3", "ray3@email.com");
//			Student tempStudent4 = new Student("Ray4", "Le4", "ray4@email.com");
//			Student tempStudent5 = new Student("Ray5", "Le5", "ray5@email.com");
			Student tempStudent6 = new Student("Ray7", "Le7", "ray7@email.com", DateUtils.parseDate("17/09/2000"));
			
			// start transaction
			session.beginTransaction();
			
			// save object
			System.out.println("Saving the student");
//			session.save(tempStudent2);
//			session.save(tempStudent3);
//			session.save(tempStudent4);
//			session.save(tempStudent5);
			session.save(tempStudent6);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		} finally {
			factory.close();
		}
	}

}
