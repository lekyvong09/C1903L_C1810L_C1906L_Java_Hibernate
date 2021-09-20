package com.ray.hibernate.demo.OneToManyBidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Course;
import com.ray.hibernate.demo.entity.Instructor;
import com.ray.hibernate.demo.entity.InstructorDetail;

public class BidirectionalDeleteCourse {

	public static void main(String[] args) {
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			// get courseID
			int theId = 1;
			Course tempCourse = session.get(Course.class, theId);
			
			// delete
			System.out.println("Delete course: " + tempCourse);
			session.delete(tempCourse);
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
			
		} finally {
			factory.close();
		}

	}

}
