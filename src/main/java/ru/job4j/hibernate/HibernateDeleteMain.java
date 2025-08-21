package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.hibernate.entity.Student;

public class HibernateDeleteMain {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            Student student = session.get(Student.class, 1);
            session.remove(student);

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
