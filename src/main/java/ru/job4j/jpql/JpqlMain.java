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

            //index parameters
            TypedQuery<Student> query4 = em.createQuery("select s from Student s where s.name like ?1 and s.avgGrade > ?2", Student.class);
            query4.setParameter(1, "%l%");
            query4.setParameter(2, 8.5);
            query4.getResultList().forEach(System.out::println);

            //named parameters
            TypedQuery<Student> query5 = em.createQuery("select s from Student s where s.name like :letter and s.avgGrade > :grade", Student.class);
            query5.setParameter("letter", "%l%");
            query5.setParameter("grade", 8.5);
            query5.getResultList().forEach(System.out::println);

            //update values
            Query query6 = em.createQuery("update Student set avgGrade = 7.0 where length(surname) > 6");
            query6.executeUpdate();

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
