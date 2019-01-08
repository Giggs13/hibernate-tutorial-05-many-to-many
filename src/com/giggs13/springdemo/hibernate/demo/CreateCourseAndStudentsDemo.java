package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Course course = new Course("Pacman - How To Score One Million Points");
            session.save(course);
            Student student1 = new Student("John", "Doe", "john.doe@gmail.com");
            Student student2 = new Student("Mary", "Public", "mary.public@gmail.com");
            course.addStudent(student1);
            course.addStudent(student2);
            session.save(student1);
            session.save(student2);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
