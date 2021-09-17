package com.ray.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Student;

public class ReadStudentDemoMSSQL {

	public static void main(String[] args) throws ParseException {
		
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernateMSSQL.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a student
			System.out.println("Create a student");
			Student tempStudent = new Student("Tommy1", "Le1", "tommy1@email.com", DateUtils.parseDate("17/09/2000"));

			
			// start transaction
			session.beginTransaction();
			
			// save object
			System.out.println("Saving the student");
			session.save(tempStudent);

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Saved student. Generated Id: " + tempStudent.getId());
			
			
			// get a new session to retrieve the student data
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Just created student: " + myStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		} finally {
			factory.close();
		}
	}

}
