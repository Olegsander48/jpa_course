package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.hibernate.entity.Student;

public class HQLMain {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            //select with condition
            Query<Student> query1 = session.createQuery("from Student where lower(name) like '%l%' and avgGrade > 8");
            query1.getResultList().forEach(System.out::println);

            //update
            Query<Student> query2 = session.createQuery("update Student set avgGrade = 10.0 where length(name) = 5");
            query2.executeUpdate();

            //delete
            Query<Student> query3 = session.createQuery("delete from Student where avgGrade < 9");
            query3.executeUpdate();

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
