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

public class OneStudentRegisterManyCourses {

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
			
			int studentId = 10;
			Student tempStudent = session.get(Student.class, studentId);
			
			// create more course
			Course tempCourse1 = new Course("JavaScript course");
			Course tempCourse2 = new Course("Angular");
			
			List<Course> listCourse = tempStudent.getCourses();
			
			if (listCourse == null) {
				listCourse = new ArrayList();
			}
			
			listCourse.add(tempCourse1);
			listCourse.add(tempCourse2);
			
			tempStudent.setCourses(listCourse);
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			System.out.println("Save completed");
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
			
		} finally {
			factory.close();
		}

	}

}
