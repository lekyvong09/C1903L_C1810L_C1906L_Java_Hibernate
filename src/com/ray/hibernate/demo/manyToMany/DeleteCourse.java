package com.ray.hibernate.demo.manyToMany;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ray.hibernate.demo.DateUtils;
import com.ray.hibernate.demo.entity.Course;
import com.ray.hibernate.demo.entity.Instructor;
import com.ray.hibernate.demo.entity.InstructorDetail;
import com.ray.hibernate.demo.entity.Student;

public class DeleteCourse {

	public static void main(String[] args) throws ParseException {
		// create session factory from configuration file
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			int courseId = 9;
			Course tempCourse = session.get(Course.class, courseId);
			
			session.delete(tempCourse);
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
			
		} finally {
			factory.close();
		}

	}

}
