package com.ray.hibernate.demo.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Instructor;
import com.ray.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			Instructor tempInstructor = new Instructor("Tommy", "Le", "tommy@email.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://tommy.channel", "Valor");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//save 
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
			
		} finally {
			factory.close();
		}

	}

}
