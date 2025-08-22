package ru.job4j.jpql;

import jakarta.persistence.*;
import ru.job4j.jpql.entity.Student;

public class CacheMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Student student1 = em.find(Student.class, 3); // select
            Student student2 = em.find(Student.class, 3); // no select because 1-lvl cache

            Student student3 = em.createQuery("select s from Student s where s.id = 3", Student.class)
                    .getSingleResult(); //select because we don't know what inside query

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
