package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.hibernate.entity.Student;

public class HibernateMain {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Student student = new Student("Edan", "Volx", 8.9);
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
