package ru.job4j.persistencecontext;

import jakarta.persistence.*;
import ru.job4j.persistencecontext.entity.Teacher;

public class PersistenceContextMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Teacher teacher1 = entityManager.find(Teacher.class, 1);
            System.out.println(teacher1);
            entityManager.close();

            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            Teacher teacher2 = entityManager2.find(Teacher.class, 1);
            System.out.println(teacher2);

            transaction.commit();
            entityManager2.close();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
