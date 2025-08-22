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

            TypedQuery<Student> query = em.createQuery("select s from Student s where name = 'Leo' ", Student.class);
            query.getResultList().forEach(System.out::println);

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
