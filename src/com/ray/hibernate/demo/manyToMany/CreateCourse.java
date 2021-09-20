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

public class CreateCourse {

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
			
			// create course
			Course tempCourse = new Course("Java - Hibernate");
			
			// create student
			Student tempStudent1 = new Student("Ray2", "Le2", "ray2@email.com", DateUtils.parseDate("20/09/2021"));
			Student tempStudent2 = new Student("Ray3", "Le3", "ray3@email.com", DateUtils.parseDate("20/09/2021"));
			
			List<Student> listStudent = new ArrayList();
			listStudent.add(tempStudent1);
			listStudent.add(tempStudent2);
			
			tempCourse.setStudents(listStudent);
			
			// save
			session.save(tempCourse);
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Save completed");
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
			
		} finally {
			factory.close();
		}

	}

}
