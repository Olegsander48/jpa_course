package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.hibernate.entity.Student;

public class HibernateSelectMain {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            Student student = session.get(Student.class, 6);
            System.out.println(student); //old data
            student.setAvgGrade(10.0); //change value after commit
            System.out.println(student); //new data locally
            session.flush(); //load data to DB
            session.refresh(student); //load new data from DB
            Thread.sleep(20000);
            System.out.println(student); //get updated student

            transaction.rollback(); //after this in db nothing changed
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
