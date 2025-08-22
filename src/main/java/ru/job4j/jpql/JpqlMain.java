package ru.job4j.jpql;

import jakarta.persistence.*;
import ru.job4j.jpql.entity.Student;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            //select * from Students where name = Leo
            TypedQuery<Student> query = em.createQuery("select s from Student s where name = 'Leo' ", Student.class);
            query.getResultList().forEach(System.out::println);

            //select * from students with 'a' in name
            TypedQuery<Student> query1 = em.createQuery("select s from Student s where name like '%a%'", Student.class);
            query1.getResultList().forEach(System.out::println);

            //select * from students with 'a' or 'A' in name
            TypedQuery<Student> query2 = em.createQuery("select s from Student s where lower(name) like '%a%'", Student.class);
            query2.getResultList().forEach(System.out::println);

            //select average of average grade
            TypedQuery<Double> query3 = em.createQuery("select avg(s.avgGrade) from Student s", Double.class);
            double average = query3.getSingleResult();
            System.out.println(average);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
