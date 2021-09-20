package com.ray.hibernate.demo.OneToManyBidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.entity.Course;
import com.ray.hibernate.demo.entity.Instructor;
import com.ray.hibernate.demo.entity.InstructorDetail;

public class BidirectionalCreateCourse {

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
			
			// get instructor from db
			int theId = 3;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			// create courses
			Course tempCourse1 = new Course("The first course");
			Course tempCourse2 = new Course("The second course");
			
			tempCourse1.setInstructor(tempInstructor);
			
			tempInstructor.addCourseToInstructor(tempCourse2);
			
			// save
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
			
		} finally {
			factory.close();
		}

	}

}
